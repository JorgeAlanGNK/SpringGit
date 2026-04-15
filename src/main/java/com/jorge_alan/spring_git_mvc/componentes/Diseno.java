package com.jorge_alan.spring_git_mvc.componentes;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.google.common.base.Strings;
import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.EstructuraNavegacion;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Diseno {

    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static final class ConstruccionNavegador {

        private EstructuraNavegacion menus = new EstructuraNavegacion();

        public void InicializarMenu(JFrame app, boolean esAccesible) {
            JMenuBar menuBar = new JMenuBar();
            menuBar.setEnabled(esAccesible);
            JMenu parentFrameMenu = null;
            for (Map.Entry<String, List<String>> menuEach : menus.getNavegaciones().entrySet()) {
                String parentMenuStr = menuEach.getKey();
                parentFrameMenu = new JMenu(parentMenuStr);
                for (String navs : menuEach.getValue()) {
                    JMenuItem childFrameMenu = new JMenuItem(navs);
                    parentFrameMenu.add(childFrameMenu);
                }
                menuBar.add(parentFrameMenu);
            }
            app.setJMenuBar(menuBar);
        }

        public boolean ComprobacionGITVersion() {
            try {
                ProcessBuilder builder = new ProcessBuilder("git", "--version");
                Process process = builder.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String comando = Strings.nullToEmpty("");
                boolean resultado = false;
                while (((comando = reader.readLine()) != null)) {
                    resultado = comando.contains("git version");
                }
                return resultado;
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
                JOptionPane.showMessageDialog(null,
                        String.format("Comprobando la carpeta " + carpeta.getAbsolutePath()));
                if (carpeta == null || Strings.isNullOrEmpty(carpeta.getAbsolutePath())) {
                    JOptionPane.showMessageDialog(null, "La carpeta no es valida para el git");
                } else {
                    ruta = carpeta.getAbsolutePath();
                }
            }
            return ruta;
        }
    }
}
