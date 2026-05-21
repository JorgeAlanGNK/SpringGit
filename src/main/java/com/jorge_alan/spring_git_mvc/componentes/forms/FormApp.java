package com.jorge_alan.spring_git_mvc.componentes.forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstruccionNavegador;
import com.jorge_alan.spring_git_mvc.datos.capaDatos.GitVisualizacion;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.BaseModelo;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoSituacion;
import com.jorge_alan.spring_git_mvc.negocios.IniciadorUsuario;

public class FormApp extends javax.swing.JFrame {

    private static Dimension winDim = Toolkit.getDefaultToolkit().getScreenSize();
    private static java.util.logging.Logger logger;
    private static ConstruccionNavegador navegador;// se encarga de verificar diseños y verificaciones de ciertos
    // componentes
    private static IniciadorUsuario manejoUsuario;// capa de negocio;
    private static ControladorFormulario controladorForm;// carga del controlador
    private static BaseModelo vistaRepo;
    private static List<com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu> panelesRepositorios;// inicio de la carga de diferentes repositorios
    private boolean validarOperaciones;

    public FormApp() {
        winDim = Toolkit.getDefaultToolkit().getScreenSize();
        logger = java.util.logging.Logger.getLogger(FormApp.class.getName());
        navegador = IniciarNavegador(this);
        manejoUsuario = CargaInicio();
        controladorForm = FormUsuario();
        vistaRepo = ModeloIdentico();
        panelesRepositorios = Lists.newArrayList();
        setVisible(true);
        LookAndFeel();
        initComponents();
        VistaInit();
    }

    private static ConstruccionNavegador IniciarNavegador(javax.swing.JFrame app) {
        if (navegador == null) {
            return new ConstruccionNavegador();
        }
        return navegador;
    }

    private static IniciadorUsuario CargaInicio() {
        if (manejoUsuario == null) {
            return new IniciadorUsuario(
                    new GitVisualizacion());
        }
        return manejoUsuario;
    }

    private static ControladorFormulario FormUsuario() {
        if (controladorForm == null) {
            controladorForm = new ControladorFormulario(manejoUsuario);
        }
        return controladorForm;
    }

    private static BaseModelo ModeloIdentico() {
        if (vistaRepo == null) {
            vistaRepo = new BaseModelo();
        }
        controladorForm.setModeloPrincipal(null);// se espera la carga del repositorio
        return vistaRepo;
    }

    public static void setSituacionDto(EstadoSituacion error) {
        vistaRepo.setSituacion(error);
    }

    private void VistaInit() {
        // vaciar el tabbedPaneActual
        this.tabbedPaneCustom1.removeAll();
        revalidate();
        repaint();
        // comenzar a tomar el primer repositorio
        if (!navegador.ComprobacionGITVersion()) {//verificamos si tiene GIT instalado
            //en caso de no tenerlo no podra realizar otra operacion
           //ansible ayuda a instalar este componente
            String mensaje = "Esta aplicación requiere forzosamente instalada la extensión GIT\\n"
                    + "favor de buscar la siguiente URL https://git-scm.com/install/";
            JOptionPane.showMessageDialog(this, mensaje, "Instalador Git no instalado",
                    JOptionPane.INFORMATION_MESSAGE);
            validarOperaciones = false;
        } else {
            validarOperaciones = true;
        }
        if (validarOperaciones) {// se carga al controlador
//            Set<String> rutasFisicasGitLocal = Sets.newHashSet();
//            String ruta = navegador.RutaFisica(this);
//            if (!Strings.isNullOrEmpty(ruta)) {
//                rutasFisicasGitLocal.add(ruta);
//            }
//            ModeloRepositorio repoView = new ModeloRepositorio();
//            repoView.setRepositorios(rutasFisicasGitLocal);
//            repoView.setRepositorioActual(ruta);
//            ActualizarControlador(repoView);
        } else {
//            ModeloRepositorio repoView = new ModeloRepositorio();
//            repoView.setRepositorios(Sets.newHashSet());
//            repoView.setRepositorioActual("");
//            repoView.setActivo(false);
//            ActualizarControlador(repoView);
        }
        // comenzamos a cargar el panel del menu principal
//        com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu repoMenu = new com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu();
//        panelesRepositorios.add(repoMenu);
//        repoMenu.Load(controladorForm);
//        // enviamos los datos a JTabbedPane para el repositorio
//        if (!vistaRepo.getFormRepositorio().getRepositorios().isEmpty()) {
//            for (String repo : vistaRepo.getFormRepositorio().getRepositorios()) {
//                int pos_repo = repo.lastIndexOf("\\");
//                String titulo = repo.substring(pos_repo).replace("\\", "").trim();
//                this.tabbedPaneCustom1.addTab(titulo, repoMenu);
//            }
//        }
        repaint();
        revalidate();
    }

    private void ActualizarControlador(ModeloRepositorio repo) {
        vistaRepo.setFormRepositorio(repo);
        controladorForm.setModelo(vistaRepo.getFormRepositorio());
    }

    private void CenterInfoModal(JDialog modal) {
        double xPos = winDim.getWidth() / 2 - 4 * 100;
        double yPos = winDim.getHeight() / 2 - 3.5 * 100;
        modal.setLocation((int) xPos, (int) yPos);
        modal.setVisible(true);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        variedadLayoutPanel = new javax.swing.JLayeredPane();
        menuNoRepoFound = new com.jorge_alan.spring_git_mvc.componentes.forms.MenuSelection();
        tabbedPaneCustom1 = new com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom();
        panelContenedorMenu1 = new com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu();
        accesoOperacion = new javax.swing.JMenuBar();
        SeccionRama = new javax.swing.JMenu();
        itemCrearRepositorio = new javax.swing.JMenuItem();
        itemSeleccionarRepositorio = new javax.swing.JMenuItem();
        SeccionConfiguración = new javax.swing.JMenu();
        ItemToken = new javax.swing.JMenuItem();
        ItemUrlRemoto = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("GitApp");
        setPreferredSize(winDim);
        setSize(winDim);

        variedadLayoutPanel.setLayout(new java.awt.CardLayout());
        variedadLayoutPanel.add(menuNoRepoFound, "card3");

        tabbedPaneCustom1.addTab("tab1", panelContenedorMenu1);

        variedadLayoutPanel.add(tabbedPaneCustom1, "card2");

        getContentPane().add(variedadLayoutPanel, java.awt.BorderLayout.CENTER);

        SeccionRama.setText("Archivos");

        itemCrearRepositorio.setText("Generar Repositorio...");
        itemCrearRepositorio.addActionListener(this::itemCrearRepositorioActionPerformed);
        SeccionRama.add(itemCrearRepositorio);

        itemSeleccionarRepositorio.setText("Agregar Repositorio");
        itemSeleccionarRepositorio.addActionListener(this::itemSeleccionarRepositorioActionPerformed);
        SeccionRama.add(itemSeleccionarRepositorio);

        accesoOperacion.add(SeccionRama);

        SeccionConfiguración.setText("Configuración");

        ItemToken.setText("Agregar Token GitHub");
        ItemToken.addActionListener(this::ItemTokenActionPerformed);
        SeccionConfiguración.add(ItemToken);

        ItemUrlRemoto.setText("Agregar Lista Remota");
        ItemUrlRemoto.addActionListener(this::ItemUrlRemotoActionPerformed);
        SeccionConfiguración.add(ItemUrlRemoto);

        accesoOperacion.add(SeccionConfiguración);

        setJMenuBar(accesoOperacion);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ItemTokenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ItemTokenActionPerformed
        controladorForm.VerificarRemoto().thenAccept((result) -> {
            ModalTokenUrl modalToken = new ModalTokenUrl(this, true);
            boolean resultado = result;
            modalToken.setActivarUrlRemoto(vistaRepo.getFormRepositorio().isActivo());
            CenterInfoModal(modalToken);
        });
    }// GEN-LAST:event_ItemTokenActionPerformed

    private void ItemUrlRemotoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ItemUrlRemotoActionPerformed
        String urlRemoto = JOptionPane.showInputDialog(this,
                "Ingrese la url de su repositorio Remoto que le proporciona GitHub de este repositorio local",
                "Repositorio GitHub", JOptionPane.INFORMATION_MESSAGE);
        manejoUsuario.ObtenerTareaPrincipal(!Strings.isNullOrEmpty(urlRemoto)).thenAccept((resultado) -> {
            ModeloRepositorio actual = vistaRepo.getFormRepositorio();
            actual.setRamasLocales(resultado.getRamasLocales());
            actual.setRamasRemotas(resultado.getRamasRemotas());
            actual.setStashes(resultado.getStashes());
            actual.setRemotosUrl(resultado.getRemotosUrl());
            actual.setActivo(resultado.getRemotosUrl().size() == 0);
            vistaRepo.setSituacion(resultado.getSituacion());
            ModalTokenUrl modalToken = new ModalTokenUrl(this, true);
            modalToken.setActivarUrlRemoto(actual.isActivo());
            CenterInfoModal(modalToken);
        });
    }// GEN-LAST:event_ItemUrlRemotoActionPerformed

    private void itemCrearRepositorioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemCrearRepositorioActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_itemCrearRepositorioActionPerformed

    private void itemSeleccionarRepositorioActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_itemSeleccionarRepositorioActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_itemSeleccionarRepositorioActionPerformed

    private void LookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ItemToken;
    private javax.swing.JMenuItem ItemUrlRemoto;
    private javax.swing.JMenu SeccionConfiguración;
    private javax.swing.JMenu SeccionRama;
    private javax.swing.JMenuBar accesoOperacion;
    private javax.swing.JMenuItem itemCrearRepositorio;
    private javax.swing.JMenuItem itemSeleccionarRepositorio;
    private com.jorge_alan.spring_git_mvc.componentes.forms.MenuSelection menuNoRepoFound;
    private com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu panelContenedorMenu1;
    private com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JLayeredPane variedadLayoutPanel;
    // End of variables declaration//GEN-END:variables
}
