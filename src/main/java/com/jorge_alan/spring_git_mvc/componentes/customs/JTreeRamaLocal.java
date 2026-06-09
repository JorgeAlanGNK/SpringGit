package com.jorge_alan.spring_git_mvc.componentes.customs;

import com.google.common.collect.Queues;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstanteIcono;
import com.jorge_alan.spring_git_mvc.componentes.extension.EstructuraNavegacion;
import com.jorge_alan.spring_git_mvc.componentes.extension.ImagenEstatica;
import java.awt.Component;
import java.awt.Dimension;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class JTreeRamaLocal extends JTree {

    private Set<RamaModelo> ramaLocal;
    private List<String> carpetas;
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
            //adaptacion de la celda mediante la longitud del texto que se va a enviar
            Dimension dimCelda = celda.getPreferredSize();
            double longitudCelda = (double) dimCelda.getWidth() * 2.5;
            dimCelda.setSize(longitudCelda, dimCelda.getHeight());
            celda.setPreferredSize(dimCelda);
            String valueRama = nodo.getUserObject().toString();
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

    public void setRamaLocal(Set<RamaModelo> ramaLocal) {
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
