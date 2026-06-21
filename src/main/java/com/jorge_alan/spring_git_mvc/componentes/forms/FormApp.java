package com.jorge_alan.spring_git_mvc.componentes.forms;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import com.google.common.base.Strings;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstruccionNavegador;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import com.jorge_alan.spring_git_mvc.modelos.modules.ModulosUsuario;

public class FormApp extends javax.swing.JFrame {

    private static Dimension winDim = Toolkit.getDefaultToolkit().getScreenSize();
    private static java.util.logging.Logger logger;
    private ModulosUsuario moduloSesion;
    private static ConstruccionNavegador navegador;
    private boolean validarOperaciones;

    public FormApp() {
        moduloSesion = new ModulosUsuario(this, navegador);
        navegador = new ConstruccionNavegador(this, moduloSesion);
        winDim = Toolkit.getDefaultToolkit().getScreenSize();
        logger = java.util.logging.Logger.getLogger(FormApp.class.getName());
        setVisible(true);
        LookAndFeel();
        initComponents();
        VistaInit();
    }

    public void VistaInit() {// funcion principal para la capacitacion del usuario
        // vaciar el tabbedPaneActual
        this.tabbedPaneCustom1.removeAll();
        revalidate();
        repaint();
        // comenzar a tomar el primer repositorio
        String ruta = null;
        if (!navegador.ComprobacionGITVersion()) {// verificamos si tiene GIT instalado
            // en caso de no tenerlo no podra realizar otra operacion
            // ansible ayuda a instalar este componente
            String mensaje = "Esta aplicación requiere forzosamente instalada la extensión GIT\\n"
                    + "favor de buscar la siguiente URL https://git-scm.com/install/";
            JOptionPane.showMessageDialog(this, mensaje, "Instalador Git no instalado",
                    JOptionPane.INFORMATION_MESSAGE);
            validarOperaciones = false;
        } else {
            validarOperaciones = true;
        }
        if (validarOperaciones) {
            List<ModeloRepositorio> repoResponse = navegador.AbrirRepositorios().join();
            boolean hayNavegaciones = repoResponse.size() > 0;
            // apertura de sesiones, si existen activos, se abriran solo los activos, sino,
            // puede escoger el repositorio que desea verificiar, para volver activarla
            if (hayNavegaciones) {
                for (ModeloRepositorio repositorio_local : repoResponse) {
                    navegador.AgregarPanelTab(repositorio_local.getRepositorioActual(), false);
                }
            } else {
                //se agrega un nuevo repositorio, en caso de no tener sesiones abiertas
                ruta = navegador.RutaFisica();
                if (Strings.isNullOrEmpty(ruta)) {
                    JOptionPane.showMessageDialog(this, "la ruta del GIT es invalida", "Ruta desconocida",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    navegador.AgregarPanelTab(ruta, true);
                }
            }
        }
        repaint();
        revalidate();
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
        menuNoRepoFound.setNavegador(moduloSesion.getNavegador());
        tabbedPaneCustom1 = new com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom();
        panelContenedorMenu1 = new com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu();
        accesoOperacion = new javax.swing.JMenuBar();
        SeccionRama = new javax.swing.JMenu();
        itemCrearRepositorio = new javax.swing.JMenuItem();
        itemSeleccionarRepositorio = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
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

        itemSeleccionarRepositorio.setText("Abrir Repositorio");
        itemSeleccionarRepositorio.addActionListener(this::itemSeleccionarRepositorioActionPerformed);
        SeccionRama.add(itemSeleccionarRepositorio);

        jMenuItem1.setText("Ingresar un Repositorio");
        SeccionRama.add(jMenuItem1);

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
        ModalTokenUrl modalToken = new ModalTokenUrl(this, true);
        CenterInfoModal(modalToken);
    }// GEN-LAST:event_ItemTokenActionPerformed

    private void ItemUrlRemotoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_ItemUrlRemotoActionPerformed
        String urlRemoto = JOptionPane.showInputDialog(this,
                "Ingrese la url de su repositorio Remoto que le proporciona GitHub de este repositorio local",
                "Repositorio GitHub", JOptionPane.INFORMATION_MESSAGE);
        moduloSesion.getManejoUsuario().ObtenerTareaPrincipal(!Strings.isNullOrEmpty(urlRemoto))
                .thenAccept((resultado) -> {
                    // ModeloRepositorio actual = vistaRepo.getFormRepositorio();
                    // actual.setRamasLocales(resultado.getRamasLocales());
                    // actual.setRamasRemotas(resultado.getRamasRemotas());
                    // actual.setStashes(resultado.getStashes());
                    // actual.setRemotosUrl(resultado.getRemotosUrl());
                    // actual.setActivo(resultado.getRemotosUrl().size() == 0);
                    // ModalTokenUrl modalToken = new ModalTokenUrl(this, true);
                    // modalToken.setActivarUrlRemoto(actual.isActivo());
                    // CenterInfoModal(modalToken);
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

    // Propiedades de solo componentes
    public com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom getTabbedPaneCustom1() {
        return tabbedPaneCustom1;
    }

    public JLayeredPane getVariedadLayoutPanel() {
        return variedadLayoutPanel;
    }

    public com.jorge_alan.spring_git_mvc.componentes.forms.MenuSelection getMenuNoRepoFound() {
        return menuNoRepoFound;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem ItemToken;
    private javax.swing.JMenuItem ItemUrlRemoto;
    private javax.swing.JMenu SeccionConfiguración;
    private javax.swing.JMenu SeccionRama;
    private javax.swing.JMenuBar accesoOperacion;
    private javax.swing.JMenuItem itemCrearRepositorio;
    private javax.swing.JMenuItem itemSeleccionarRepositorio;
    private javax.swing.JMenuItem jMenuItem1;
    private com.jorge_alan.spring_git_mvc.componentes.forms.MenuSelection menuNoRepoFound;
    private com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu panelContenedorMenu1;
    private com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom tabbedPaneCustom1;
    private javax.swing.JLayeredPane variedadLayoutPanel;
    // End of variables declaration//GEN-END:variables
}
