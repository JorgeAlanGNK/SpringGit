package com.jorge_alan.spring_git_mvc.componentes;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JDialog;

import com.google.common.base.Strings;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public final class Diseno {

    private Diseno() throws InterruptedException {
        throw new InterruptedException("No se puede instanciar");
    }

    public static final class ConstruccionNavegador {

        public boolean ComprobacionGITVersion() {
            try {
                ProcessBuilder builder = new ProcessBuilder("git", "--version");
                Process process = builder.start();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    String comando = Strings.nullToEmpty("");
                    boolean resultado = false;
                    while (!Strings.isNullOrEmpty(comando = reader.readLine())) {
                        resultado = comando.contains("git version");
                    }
                    return resultado;
                }
            } catch (Exception e) {
                System.out.println("Error: Git Version no ejecutado");
                System.out.println(e.getMessage());
            }
            return false;
        }

        public String RutaFisica(JFrame app) {
            JFileChooser exploradorSO = new JFileChooser();
            exploradorSO.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = exploradorSO.showOpenDialog(app);
            String ruta = "";
            if (result == JFileChooser.APPROVE_OPTION) {
                File carpeta = exploradorSO.getSelectedFile();
                JOptionPane.showMessageDialog(app,
                        String.format("Comprobando la carpeta " + carpeta.getAbsolutePath()));
                if (carpeta == null || Strings.isNullOrEmpty(carpeta.getAbsolutePath())) {
                    JOptionPane.showMessageDialog(app, "La carpeta no es valida para el git");
                } else {
                    ruta = carpeta.getAbsolutePath();
                }
            } else if (result == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(app, "Repositorio no reconocido, favor de seleccionar el repositorio Adecuado", "Repositorio no seleccionado", JOptionPane.WARNING_MESSAGE);
            }
            return ruta;
        }

    }

    public static final class ConstanteIcono {

        private ConstanteIcono() throws InterruptedException {
            throw new InterruptedException("no se puede instanciar " + this.getClass().getName());
        }

        public static final String ICONO_BTN_DOWN = "static/arrow-down-svgrepo-com.svg";
        public static final String ICONO_BTN_UP = "static/arrow-up-svgrepo-com.svg";
        public static final String ICONO_RAMA = "static/branch-svgrepo-com.svg";
        public static final String ICONO_STASH = "static/package-svgrepo-com.svg";
        public static final String ICONO_REMOTO = "static/git-svgrepo-com.svg";
        public static final String ICONO_RAMA_SOLIDO_SELECCION = "static/branch-solid-svgrepo-com.svg";
        public static final String ICONO_RAMA_CERRADO = "static/git-branch-svgrepo-com.svg";
        public static final String ICONO_RAMA_ABIERTO = "static/branch-solid-svgrepo-com.svg";
        public static final String ICONO_FOLDER = "static/folder-2-svgrepo-com.svg";
        public static final String ICONO_GIT_LOGO = "static/github-color-svgrepo-com.svg";
        public static final String ICONO_STASH_LOGO_ITEM = "static/json-svgrepo-com.svg";
        public static final String ICONO_AGREGAR_TAB = "static/plus-1469-svgrepo-com.svg";
    }
}
