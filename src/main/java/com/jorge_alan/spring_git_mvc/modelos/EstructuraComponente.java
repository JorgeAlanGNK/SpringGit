package com.jorge_alan.spring_git_mvc.modelos;

import java.util.Queue;
import javax.swing.Icon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtension;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtensionImpl;
import javax.swing.JButton;
import javax.swing.JLabel;

import lombok.ToString;

public final class EstructuraComponente {

    private EstructuraComponente() throws IllegalArgumentException {
        throw new IllegalArgumentException("No se puede realizar la operacion del cliente  ");
    }

    public static final class ImagenEstatica {

        private IconoExtension iconoExtension = new IconoExtensionImpl();

        public ImagenEstatica() {
        }
        
        //Clase relacionada con ConstanteIcono
        public Icon GenerarIcono(String nombre, int ancho, int altura) {
            return this.iconoExtension.MostrarIcono(nombre, ancho, ancho);
        }

    }

    @ToString
    public static final class EstructuraNavegacion {

        private DefaultMutableTreeNode branchCurrent;
        private Icon generalIcon;
        private DefaultTreeCellRenderer customCelda;

        public DefaultMutableTreeNode getBranchCurrent() {
            return branchCurrent;
        }

        public void setBranchCurrent(DefaultMutableTreeNode branchCurrent) {
            this.branchCurrent = branchCurrent;
        }

        public void VaciarRamas(DefaultTreeModel init, String nameObject) {
            DefaultMutableTreeNode parent = (DefaultMutableTreeNode) init.getRoot();
            parent.removeAllChildren();
            parent.setUserObject(nameObject);
            init.reload();
        }

        public void ListarItems(DefaultMutableTreeNode parent, Queue<String> items) {
            boolean comprobacion = !items.isEmpty();
            while (comprobacion) {
                String info = items.poll();
                parent.add(new DefaultMutableTreeNode(info));
            }
        }

        public void DefaultTreeNode(boolean hayCarpetas, DefaultMutableTreeNode parent, Queue<String> carpetas) {
            //generar un nuevo InitBranch para separar las ramas en que estado de carpeta estan
            if (hayCarpetas) {
                while (!carpetas.isEmpty()) {
                    DefaultMutableTreeNode encontrado = null;
                    String dirActual = carpetas.poll();
                    for (int i = 0; i < parent.getChildCount(); i++) {
                        DefaultMutableTreeNode child = (DefaultMutableTreeNode) parent.getChildAt(i);
                        if (child.getUserObject().equals(dirActual)) {
                            encontrado = child;
                            break;
                        }
                    }
                    if (encontrado != null) {
                        parent = encontrado;
                    } else {
                        DefaultMutableTreeNode nuevo = new DefaultMutableTreeNode(dirActual);
                        parent.add(nuevo);
                        parent = nuevo;
                    }
                }
            } else {
                String dirFinal = carpetas.poll();
                DefaultMutableTreeNode temp = new DefaultMutableTreeNode(dirFinal);
                parent.add(temp);
            }
        }

    }
}
