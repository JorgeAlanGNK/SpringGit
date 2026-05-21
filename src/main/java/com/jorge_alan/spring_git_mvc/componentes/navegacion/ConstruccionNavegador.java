package com.jorge_alan.spring_git_mvc.componentes.navegacion;

import java.awt.CardLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import com.google.common.base.Strings;

public class ConstruccionNavegador {
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
            JOptionPane.showMessageDialog(app,
                    "Repositorio no reconocido, favor de seleccionar el repositorio Adecuado",
                    "Repositorio no seleccionado", JOptionPane.WARNING_MESSAGE);
        }
        return ruta;
    }

    public void CambiarPanel(CardLayout cardLayout, JComponent componente) {
        Container parentContainer = componente.getParent();
    }
}
