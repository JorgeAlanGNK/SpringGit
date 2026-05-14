package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstruccionNavegador;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.ComandoOperacion;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.GitVisualizacion;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.BaseModelo;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoEnum;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoSituacion;
import com.jorge_alan.spring_git_mvc.negocios.IniciadorUsuario;

import javax.swing.JDialog;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.Set;
import javax.swing.JOptionPane;

public class FormApp extends javax.swing.JFrame {

    private static final Dimension winDim = Toolkit.getDefaultToolkit().getScreenSize();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormApp.class.getName());
    private static ConstruccionNavegador navegador = IniciarNavegador();//se encarga de verificar diseños y verificaciones de ciertos componentes
    private static IniciadorUsuario manejoUsuario = CargaInicio();//capa de negocio;
    private static ControladorFormulario controladorForm = FormUsuario();//carga del controlador
    private static BaseModelo vistaRepo = ModeloIdentico();
    private boolean validarOperaciones;
    private String LecturaRepositorio;

    private static ConstruccionNavegador IniciarNavegador() {
        if (navegador == null) {
            return new ConstruccionNavegador();
        }
        return navegador;
    }

    private static IniciadorUsuario CargaInicio() {
        if (manejoUsuario == null) {
            return new IniciadorUsuario(
                    new GitVisualizacion(),
                    new ComandoOperacion());
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
        return vistaRepo;
    }

    public static void setSituacionDto(EstadoSituacion error) {
        vistaRepo.setSituacion(error);
    }

    private void VistaInit() {
        if (!navegador.ComprobacionGITVersion()) {
            String mensaje = "Esta aplicación requiere forzosamente instalada la extensión GIT\\n"
                    + "favor de buscar la siguiente URL https://git-scm.com/install/";
            JOptionPane.showMessageDialog(this, mensaje, "Instalador Git no instalado", JOptionPane.INFORMATION_MESSAGE);
            validarOperaciones = false;
        } else {
            validarOperaciones = true;
        }
        if (validarOperaciones) {
            Set<String> rutasFisicasGitLocal = Sets.newHashSet();
            String ruta = navegador.RutaFisica(this);
            if (!Strings.isNullOrEmpty(ruta)) {
                rutasFisicasGitLocal.add(ruta);
            }
            rutasFisicasGitLocal.add(ruta);
            ModeloRepositorio repoView = new ModeloRepositorio();
            repoView.setRepositorios(rutasFisicasGitLocal);
            repoView.setRepositorioActual(ruta);
            this.vistaRepo.setFormRepositorio(repoView);
        } else {
            ModeloRepositorio repoView = new ModeloRepositorio();
            repoView.setRepositorios(Sets.newHashSet());
            repoView.setRepositorioActual("");
            repoView.setActivo(false);
            this.vistaRepo.setFormRepositorio(repoView);
        }
        this.panelContenedorMenu1.Load(controladorForm, vistaRepo.getFormRepositorio());
    }
    
    private void EnviarRepositorioTab() {
        vistaRepo.getFormRepositorio();
    }

    public FormApp() {
        setVisible(true);
        LookAndFeel();
        initComponents();
        VistaInit();
    }

    private void CenterInfoModal(JDialog modal) {
        double xPos = winDim.getWidth() / 2 - 4 * 100;
        double yPos = winDim.getHeight() / 2 - 3.5 * 100;
        modal.setLocation((int) xPos, (int) yPos);
        modal.setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

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

        tabbedPaneCustom1.addTab("tab1", panelContenedorMenu1);

        getContentPane().add(tabbedPaneCustom1, java.awt.BorderLayout.CENTER);

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

    private void ItemTokenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemTokenActionPerformed
        controladorForm.VerificarRemoto().thenAccept((result) -> {
            ModalTokenUrl modalToken = new ModalTokenUrl(this, true);
            boolean resultado = result;
            modalToken.setActivarUrlRemoto(vistaRepo.getFormRepositorio().isActivo());
            CenterInfoModal(modalToken);
        });
    }//GEN-LAST:event_ItemTokenActionPerformed

    private void ItemUrlRemotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUrlRemotoActionPerformed
        String urlRemoto = JOptionPane.showInputDialog(this, "Ingrese la url de su repositorio Remoto que le proporciona GitHub de este repositorio local", "Repositorio GitHub", JOptionPane.INFORMATION_MESSAGE);
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
    }//GEN-LAST:event_ItemUrlRemotoActionPerformed

    private void itemCrearRepositorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCrearRepositorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemCrearRepositorioActionPerformed

    private void itemSeleccionarRepositorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSeleccionarRepositorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemSeleccionarRepositorioActionPerformed

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
    private com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu panelContenedorMenu1;
    private com.jorge_alan.spring_git_mvc.componentes.customs.TabbedPaneCustom tabbedPaneCustom1;
    // End of variables declaration//GEN-END:variables
}
