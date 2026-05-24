package com.jorge_alan.spring_git_mvc.componentes.navegacion;

import java.awt.CardLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom;
import com.jorge_alan.spring_git_mvc.componentes.forms.ControladorFormulario;
import com.jorge_alan.spring_git_mvc.componentes.forms.FormApp;
import com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;

public class ConstruccionNavegador {

    private FormApp app;
    private List<PanelContenedorMenu> panelNavegador;
    private Map<String, ModeloRepositorio> modelosRepositorios;// permite enviar un nuevo cambio al controlador
    private int cantidadPaneles;
    private Set<String> rutas;
    private ControladorFormulario controlador;// no se puede instanciar, intentar cargar el controlador desde el formApp

    public ConstruccionNavegador(FormApp app) {
        this.app = app;
        this.rutas = Sets.newHashSet();
        this.modelosRepositorios = Maps.newHashMap();
        this.panelNavegador = Lists.newArrayList();
        this.cantidadPaneles = 0;
    }

    // Operaciones para un navegador principal
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

    public String RutaFisica() {
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
        cardLayout.show(parentContainer, "card1");
    }

    public void AgregarPanelTab(String nuevoEnlace) {
        // falta quitar la ruta que se puede obtener esta informacion del modelo.
        int separatorFinal = nuevoEnlace.lastIndexOf("\\");
        if (separatorFinal != -1) {
            String tempEnlace = nuevoEnlace.substring(separatorFinal);
            ModeloRepositorio modelo = GenerarRepo(nuevoEnlace);
            // se comienza a cargar el panel del menu principal
            
            this.modelosRepositorios.put(tempEnlace, modelo);
            this.controlador.setModelo(modelo);
            rutas.add(nuevoEnlace);
            PanelContenedorMenu nuevoPanel = new PanelContenedorMenu();
            nuevoPanel.Load(this.controlador);
            this.panelNavegador.add(nuevoPanel);
            this.cantidadPaneles = panelNavegador.size();
            this.app.getTabbedPaneCustom1().addTab(tempEnlace, nuevoPanel);
            CardLayout cardLayout = (CardLayout) app.getVariedadLayoutPanel().getLayout();
            cardLayout.show(app.getVariedadLayoutPanel(), CardConstante.CARD_TABBED_PANE_CUSTOM);
            app.repaint();
            app.revalidate();
        }
    }

    public Set<String> getRutas() {
        return rutas;
    }

    private ModeloRepositorio GenerarRepo(String ruta) {
        ModeloRepositorio modelo = new ModeloRepositorio();
        modelo.setRepositorios(rutas);
        modelo.setRepositorioActual(ruta);
        return modelo;
    }

    // Propiedades
    public int getCantidadPaneles() {
        return cantidadPaneles;
    }

    public List<PanelContenedorMenu> ListarPaneles() {
        return panelNavegador;
    }

    public ControladorFormulario getControlador() {
        return controlador;
    }

    public void setControlador(ControladorFormulario controlador) {
        this.controlador = controlador;
    }

    public static final class CardConstante {
        public static final String CARD_MENU_SELECTION = "card3";
        public static final String CARD_TABBED_PANE_CUSTOM = "card2";
    }

}
