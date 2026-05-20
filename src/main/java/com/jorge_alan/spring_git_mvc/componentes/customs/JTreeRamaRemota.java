package com.jorge_alan.spring_git_mvc.componentes.customs;

import com.google.common.collect.Queues;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstanteIcono;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.EstructuraNavegacion;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtension;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtensionImpl;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoSituacion;
import java.awt.Color;
import java.awt.Component;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class JTreeRamaRemota extends JTree {
    
    private Set<RamaModelo> ramaRemotos;
    private final IconoExtension extIcono = new IconoExtensionImpl();
    private EstadoSituacion situacionUsuario;
    private final EstructuraNavegacion navegacion = new EstructuraNavegacion();
    
    public JTreeRamaRemota() {
        VaciarRamas();
        setDragEnabled(false);
        setOpaque(true);
        setCellRenderer(new DefaultTreeCellRenderer() {
            @Override
            public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {
                //rgba(36, 161, 197, 1)
                Component cell = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
                if (selected) {
                    setBackgroundSelectionColor(new Color(36, 161, 197));
                    setBackground(new Color(36, 161, 197));
                } else {
                    setBackgroundNonSelectionColor(new Color(36, 161, 197));
                    setBackground(new Color(36, 161, 197));
                }
                //se envia para restrable
                DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) value;
                leafIcon = extIcono.MostrarIcono(ConstanteIcono.ICONO_RAMA_SOLIDO_SELECCION, 20, 20);
                closedIcon = extIcono.MostrarIcono(ConstanteIcono.ICONO_FOLDER, 20, 20);
                openIcon = extIcono.MostrarIcono(ConstanteIcono.ICONO_FOLDER, 20, 20);
                String valueRama = nodo.getUserObject().toString();
                return this;
            }
        });
    }
    
    private void VaciarRamas() {
        this.navegacion.VaciarRamas((DefaultTreeModel)getModel(), "origin");
    }

    public Set<RamaModelo> getRamaRemotos() {
        return ramaRemotos;
    }
    
    public EstadoSituacion getSituacionUsuario() {
        return situacionUsuario;
    }

    public void setRamaRemotos(Set<RamaModelo> ramaRemotos, EstadoSituacion situacionUsuario) {
        this.ramaRemotos = ramaRemotos;
        this.situacionUsuario = situacionUsuario;
        RellenarRemotos();
    }

    private void RellenarRemotos() {
        DefaultTreeModel modelo = (DefaultTreeModel) getModel();
        DefaultMutableTreeNode root = (DefaultMutableTreeNode) modelo.getRoot();//el parent Actual
        for (RamaModelo estructuraRama : ramaRemotos) {
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
