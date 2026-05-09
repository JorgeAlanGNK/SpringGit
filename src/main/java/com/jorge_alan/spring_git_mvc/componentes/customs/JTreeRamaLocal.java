package com.jorge_alan.spring_git_mvc.componentes.customs;

import com.google.common.collect.Queues;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstanteIcono;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.EstructuraNavegacion;
import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.ImagenEstatica;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtensionImpl;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtension;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.EstadoSituacion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class JTreeRamaLocal extends JTree {

    private Set<RamaModelo> ramaLocal;
    private List<String> carpetas;
    private EstadoSituacion situacionUsuario;
    private final EstructuraNavegacion navegacion = new EstructuraNavegacion();
    private ImagenEstatica imagenes = new ImagenEstatica();
    
    private DefaultTreeCellRenderer renderCell = new DefaultTreeCellRenderer() {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean noHasChildren, int row, boolean hasFocus) {
            Component cell = super.getTreeCellRendererComponent(tree, value, selected, expanded, noHasChildren, row, hasFocus);
            openIcon = imagenes.GenerarIcono(ConstanteIcono.ICONO_FOLDER, 20, 20);
            closedIcon = imagenes.GenerarIcono(ConstanteIcono.ICONO_FOLDER, 20, 20);
            leafIcon = imagenes.GenerarIcono(ConstanteIcono.ICONO_RAMA_SOLIDO_SELECCION, 20, 20);
            Icon folder = imagenes.GenerarIcono(ConstanteIcono.ICONO_FOLDER, 20, 20);
            Icon rama = imagenes.GenerarIcono(ConstanteIcono.ICONO_RAMA_SOLIDO_SELECCION, 20, 20);
            //se envia para restrable
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
            CeldaPersonalizada celda = new CeldaPersonalizada();
            //logica para realizar la celda si tiene elementos o no
            if (noHasChildren) {
                celda.setLeafIcon(rama);
            } else {
                celda.setLeafIcon(folder);
            }
            //adaptacion de la celda mediante la longitud del texto que se va a enviar
            Dimension dimCelda = celda.getPreferredSize();
            double longitudCelda = (double) dimCelda.getWidth() * 2.5;
            dimCelda.setSize(longitudCelda, dimCelda.getHeight());
            celda.setPreferredSize(dimCelda);
            String valueRama = nodo.getUserObject().toString();
            celda.setNombreRama(valueRama);
            return this;
        }
    };

    public JTreeRamaLocal() {
        VaciarRamas();
        setDragEnabled(false);
        setOpaque(true);
        setCellRenderer(renderCell);
    }

    public Set<RamaModelo> getRamaLocal() {
        return ramaLocal;
    }

    public void setRamaLocal(Set<RamaModelo> ramaLocal, EstadoSituacion response) {
        this.ramaLocal = ramaLocal;
        RellenarRamas();
    }

    public List<String> getCarpetas() {
        return carpetas;
    }

    public void setCarpetas(List<String> carpetas) {
        this.carpetas = carpetas;
    }

    private void VaciarRamas() {
        this.navegacion.VaciarRamas((DefaultTreeModel) getModel(), "Branches");
    }

    private void RellenarRamas() {
        DefaultTreeModel modelo = (DefaultTreeModel) getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) modelo.getRoot();//el parent Actual
        for (RamaModelo estructuraRama : ramaLocal) {
            Queue<String> carpetas = null;
            if (estructuraRama.isCarpeta()) {
                carpetas = Queues.newArrayDeque(estructuraRama.getCarpetas());
            } else {
                carpetas = Queues.newArrayDeque();
            }
            carpetas.add(estructuraRama.getNombreRama());
            this.navegacion.DefaultTreeNode(
                    estructuraRama.isCarpeta(),
                    root,
                    carpetas
            );
        }
    }

}
