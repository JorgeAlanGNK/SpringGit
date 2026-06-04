package com.jorge_alan.spring_git_mvc.componentes.navegacion;

import java.awt.CardLayout;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom;
import com.jorge_alan.spring_git_mvc.componentes.forms.ControladorFormulario;
import com.jorge_alan.spring_git_mvc.componentes.forms.FormApp;
import com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoEnum;

public class ConstruccionNavegador {

    private FormApp app;
    private List<PanelContenedorMenu> panelNavegador;
    private Map<String, ModeloRepositorio> modelosRepositorios;// permite enviar un nuevo cambio al controlador
    private int cantidadPaneles;
    private Set<String> rutas;
    private ControladorFormulario controlador;// no se puede instanciar, intentar cargar el controlador desde el formApp
    // verificar si esta mostrando un PanelContenedorMenu
    private boolean muestraRepos;

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

    public void AgregarPanelTab(String nuevoEnlace) {
        // falta quitar la ruta que se puede obtener esta informacion del modelo.
        int separatorFinal = nuevoEnlace.lastIndexOf("\\");
        if (separatorFinal != -1) {
            String tempEnlace = nuevoEnlace.substring(separatorFinal).replace("\\", "");
            ModeloRepositorio modelo = new ModeloRepositorio();
            modelo.setRepositorioActual(nuevoEnlace);
            modelo.setRepositorioActual(nuevoEnlace);
            // se comienza a cargar el panel del menu principal
            this.modelosRepositorios.put(tempEnlace, modelo);
            this.controlador.setModelo(modelo);
            rutas.add(nuevoEnlace);
            PanelContenedorMenu nuevoPanel = new PanelContenedorMenu();
            nuevoPanel.LoadAsync(this.controlador).thenAccept((response) -> {// los metodos asincronos deben de devolver
                // el valor
                SwingUtilities.invokeLater(() -> {
                    if (response.getSituacion().getTipoEnum() == EstadoEnum.OK) {
                        // cambiar y cargar el repositorio actual
                        nuevoPanel.getRamaLocalArea().setRamaLocal(response.getRamasLocales());
                        nuevoPanel.getRamaRemotoOrigin().setRamaRemotos(response.getRamasRemotas());
                        nuevoPanel.getStashAreaPanel().setStashModelo(response.getStashes());
                        nuevoPanel.setResultadoModelo(response);
                        this.panelNavegador.add(nuevoPanel);
                        this.cantidadPaneles = panelNavegador.size();
                        this.app.getTabbedPaneCustom1().addTab(tempEnlace, nuevoPanel);
                        // se cambia el panel
                        CardLayout cardLayout = (CardLayout) app.getVariedadLayoutPanel().getLayout();
                        cardLayout.show(app.getVariedadLayoutPanel(), CardConstante.CARD_TABBED_PANE_CUSTOM);
                        muestraRepos = app.getTabbedPaneCustom1().getTabCount() > 0;
                        // ingresamos a la base de datos
                        ObtenerPanelSeleccionado(!response.getRamasRemotas().isEmpty());
                        app.repaint();
                        app.revalidate();
                    } else {
                        muestraRepos = false;
                        JOptionPane.showMessageDialog(app, String.format(
                                "El repositorio en la ruta %s no es valida, favor de generar uno o ingresar uno existente",
                                nuevoEnlace),
                                "Repositorio GIT no valido", JOptionPane.ERROR_MESSAGE);
                    }
                });
            });// aqui obtiene la informacion del repositorio

        }
    }

    public Set<String> getRutas() {
        return rutas;
    }

    public void CerrarTab(String buscarRepo) {// Cierra el tab, pero no elimina el Tab
        TabbedPaneCustom tabShow = app.getTabbedPaneCustom1();
        int tabCount = tabShow.getTabCount();
        for (int compIndex = 0; compIndex < tabCount; compIndex++) {
            PanelContenedorMenu menuRemover = (PanelContenedorMenu) tabShow.getComponentAt(compIndex);
            if (menuRemover.getResultadoModelo().getRepositorioActual() == buscarRepo) {
                tabShow.remove(menuRemover);
                panelNavegador.remove(menuRemover);
                cantidadPaneles = panelNavegador.size();
            }
        }
        app.repaint();
        app.revalidate();
    }

    public void EliminarRepo(String buscarRepo) {// Cierra el Repo
        TabbedPaneCustom tabShow = app.getTabbedPaneCustom1();
        int tabCount = tabShow.getTabCount();
        PanelContenedorMenu menuRemover = null;
        for (int compIndex = 0; compIndex < tabCount; compIndex++) {
            menuRemover = (PanelContenedorMenu) tabShow.getComponentAt(compIndex);
            if (menuRemover.getResultadoModelo().getRepositorioActual() == buscarRepo) {
                tabShow.remove(menuRemover);
                panelNavegador.remove(menuRemover);
            }
        }
    }

    public void ObtenerPanelSeleccionado(boolean activarRemoto) {
        // se puede verificar si el repositorio tiene una lista de repositorios
        // constantemente se va a estar verficando la URL en caso de error
        if (muestraRepos) {
            int index = app.getTabbedPaneCustom1().getSelectedIndex();
            PanelContenedorMenu menu_actual = (PanelContenedorMenu) app.getTabbedPaneCustom1().getComponentAt(index);
            ModeloRepositorio repo = menu_actual.getResultadoModelo();
            Consumer<Boolean> execute = (resultado) -> {
                if (resultado) {
                    repo.setActivo(true);
                } else {
                    repo.setActivo(false);
                }
            };
            if (repo.getSituacion().getTipoEnum() == EstadoEnum.OK) {
                menu_actual.getControlador().IngresarRepositorio(repo.getRepositorioActual(), activarRemoto)
                        .thenAccept(execute);
            }
        } else {
            JOptionPane.showMessageDialog(app,
                    "No hay repositorios para ingresar, favor de seleccionar uno o generar uno nuevo",
                    "No hay repositorios", JOptionPane.INFORMATION_MESSAGE);
        }
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

    private static final class CardConstante {

        public static final String CARD_MENU_SELECTION = "card3";
        public static final String CARD_TABBED_PANE_CUSTOM = "card2";
    }

}
