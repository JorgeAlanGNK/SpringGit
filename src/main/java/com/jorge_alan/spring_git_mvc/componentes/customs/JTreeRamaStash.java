package com.jorge_alan.spring_git_mvc.componentes.customs;

import com.google.common.collect.Queues;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtension;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtensionImpl;
import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.EstructuraNavegacion;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.EstadoSituacion;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstanteIcono;

import java.awt.Component;
import java.util.List;
import java.util.Queue;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.ImagenEstatica;


public class JTreeRamaStash extends JTree {

    private List<StashModelo> stashModelo;
    private final EstructuraNavegacion navegacion = new EstructuraNavegacion();
    private ImagenEstatica imagenes = new ImagenEstatica();
    
    private DefaultTreeCellRenderer renderCell = new DefaultTreeCellRenderer() {
        @Override
        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            leafIcon = imagenes.GenerarIcono(ConstanteIcono.ICONO_STASH_LOGO_ITEM, 20, 20);
            openIcon = imagenes.GenerarIcono(ConstanteIcono.ICONO_FOLDER, 20, 20);
            closedIcon = imagenes.GenerarIcono(ConstanteIcono.ICONO_FOLDER, 20, 20);
            return this;
        }
    };

    private EstadoSituacion situacionUsuario;

    public JTreeRamaStash() {
        VaciarRamas();
        setDragEnabled(false);
        setOpaque(true);
        setCellRenderer(renderCell);
    }

    private void VaciarRamas() {
        this.navegacion.VaciarRamas((DefaultTreeModel) getModel(), "Stashes");
    }

    public List<StashModelo> getStashModelo() {
        return stashModelo;
    }

    public void setStashModelo(List<StashModelo> stashModelo) {
        this.stashModelo = stashModelo;
        RellenarStashes();
    }

    public EstadoSituacion getSituacionUsuario() {
        return situacionUsuario;
    }

    public void setSituacionUsuario(EstadoSituacion situacionUsuario) {
        this.situacionUsuario = situacionUsuario;
    }

    private void RellenarStashes() {
        if (this.situacionUsuario.getEnumResult() == VistasModelos.EstadoEnum.OK && !this.stashModelo.isEmpty()) {
            DefaultTreeModel modeloActual = (DefaultTreeModel) getModel();
            DefaultMutableTreeNode root = (DefaultMutableTreeNode) modeloActual.getRoot();
            Queue<String> items = Queues.newArrayDeque();
            for (StashModelo objStash : this.stashModelo) {
                items.add(objStash.getStash());
            }
            this.navegacion.ListarItems(root, items);
        }
    }
}
