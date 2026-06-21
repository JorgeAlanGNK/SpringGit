package com.jorge_alan.spring_git_mvc.componentes.navegacion;

import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom;
import com.jorge_alan.spring_git_mvc.componentes.forms.FormApp;
import com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import com.jorge_alan.spring_git_mvc.modelos.modules.ModulosUsuario;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoEnum;
import com.jorge_alan.spring_git_mvc.negocios.IniciadorUsuario;

public class ConstruccionNavegador {

    private FormApp app;
    private List<PanelContenedorMenu> panelNavegador;
    private Set<String> rutas;
    // verificar si esta mostrando un PanelContenedorMenu
    private boolean muestraRepos;

    //comenzamos a cargar los modulos para realizar operaciones
    private static ModulosUsuario cargaModulo;

    public ConstruccionNavegador(FormApp app, ModulosUsuario cargaModulo) {
        ConstruccionNavegador.cargaModulo = cargaModulo;
        this.app = app;
        this.rutas = Sets.newHashSet();
        this.panelNavegador = Lists.newArrayList();
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

    public void AgregarPanelTab(String nuevoEnlace, boolean insertarRepositorio) {
        // falta quitar la ruta que se puede obtener esta informacion del modelo.
        int separatorFinal = nuevoEnlace.lastIndexOf("\\");
        if (separatorFinal != -1) {
            String tempEnlace = nuevoEnlace.substring(separatorFinal).replace("\\", "");
            ModeloRepositorio modelo = new ModeloRepositorio();
            modelo.setRepositorioActual(nuevoEnlace);
            IniciadorUsuario moduloActual = cargaModulo.getManejoUsuario();
            // se comienza a cargar el panel del menu principal
            rutas.add(nuevoEnlace);
            //prepararmos los datos para el panel y el cambio del tabbedPane
            PanelContenedorMenu nuevoPanel = new PanelContenedorMenu();
            nuevoPanel.setModuloIniciadorUsuario(moduloActual);
            nuevoPanel.setResultadoModelo(modelo);
            nuevoPanel.LoadComponentes(true).thenAcceptAsync((response) -> {
                SwingUtilities.invokeLater(() -> {
                    if (response.getSituacion().getTipoEnum() == EstadoEnum.OK) {
                        this.panelNavegador.add(nuevoPanel);
                        this.app.getTabbedPaneCustom1().addTab(tempEnlace, nuevoPanel);
                        //se carga el modelo
                        nuevoPanel.getRamaLocalArea().setRamaLocal(response.getRamasLocales());
                        nuevoPanel.getRamaRemotoOrigin().setRamaRemotos(response.getRamasRemotas());
                        nuevoPanel.getStashAreaPanel().setStashModelo(response.getStashes());
                        //cambiamos de layout del menu, y hacemos la API CardLayout
                        CardLayout cardLayout = (CardLayout) app.getVariedadLayoutPanel().getLayout();
                        cardLayout.show(app.getVariedadLayoutPanel(), CardConstante.CARD_TABBED_PANE_CUSTOM);
                        muestraRepos = app.getTabbedPaneCustom1().getTabCount() > 0;
                        if (insertarRepositorio) {
                            
                        }
                    } else {
                        muestraRepos = false;
                        JOptionPane.showMessageDialog(app, String.format(
                                "El repositorio en la ruta %s no es valida, favor de generar uno o ingresar uno existente",
                                nuevoEnlace),
                                "Repositorio GIT no valido", JOptionPane.ERROR_MESSAGE);
                    }
                    app.repaint();
                    app.revalidate();
                });
            });
            ModeloRepositorio response = nuevoPanel.getResultadoModelo();
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

    public CompletableFuture<List<ModeloRepositorio>> AbrirRepositorios() {
        IniciadorUsuario modulo_navegacion = cargaModulo.getManejoUsuario();
        //detectamos los repositorios que se quedaron abiertos
        return modulo_navegacion.AperturaAplicativo().thenCompose(repositorios -> {
            if (repositorios.isEmpty()) {
                return CompletableFuture.completedFuture(Lists.newArrayList());
            }
            List<CompletableFuture<ModeloRepositorio>> futuros = repositorios.stream()
                    .map(cadena -> {
                        modulo_navegacion.EnviarRepositorio(cadena);
                        return modulo_navegacion.ObtenerTareaPrincipal(true);
                    })
                    .collect(Collectors.toList());
            CompletableFuture<Integer> promesaTamano = new CompletableFuture<>();
            return CompletableFuture.allOf(futuros.toArray(CompletableFuture[]::new))
                    .thenApply(v -> {
                        return futuros.stream().map(CompletableFuture::join).collect(Collectors.toList());
                    })
                    .thenApplyAsync((resultados) -> {
                        List<ModeloRepositorio> listaRepositorios = Lists.newArrayList();
                        for (ModeloRepositorio resultado : resultados) {
                            PanelContenedorMenu menu = new PanelContenedorMenu();
                            menu.setResultadoModelo(resultado);
                            listaRepositorios.add(resultado);
                            menu.LoadComponentes(false);
                            panelNavegador.add(menu);
                            app.getTabbedPaneCustom1().add(menu);
                        }
                        app.repaint();
                        app.revalidate();
                        // 3. Retornamos el tamaño real ya actualizado
                        return listaRepositorios;
                    }, SwingUtilities::invokeLater);
        });
    }

    // Propiedades
    public List<PanelContenedorMenu> ListarPaneles() {
        return panelNavegador;
    }

    private static final class CardConstante {

        public static final String CARD_MENU_SELECTION = "card3";
        public static final String CARD_TABBED_PANE_CUSTOM = "card2";
    }

}
