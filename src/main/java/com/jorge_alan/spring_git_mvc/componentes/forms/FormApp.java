package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstruccionNavegador;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.ComandoOperacion;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.GitVisualizacion;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.SeleccionRepositorioForm;
import com.jorge_alan.spring_git_mvc.negocios.ActualizadorMenu.CargaUsuario;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtension;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtensionImpl;
import javax.swing.JDialog;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class FormApp extends javax.swing.JFrame {

    private static final Dimension winDim = Toolkit.getDefaultToolkit().getScreenSize();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormApp.class.getName());
    private static ConstruccionNavegador navegador = IniciarNavegador();//se encarga de verificar diseños y verificaciones de ciertos componentes
    private static CargaUsuario manejoUsuario = InicioUsuario();//capa de negocio;
    private static ControladorFormulario controladorForm = FormUsuario();//carga del controlador
    private static SeleccionRepositorioForm vistaRepo;
    private boolean validarOperaciones;

    private static ConstruccionNavegador IniciarNavegador() {
        if (navegador == null) {
            return new ConstruccionNavegador();
        }
        return navegador;
    }

    private static CargaUsuario InicioUsuario() {
        if (manejoUsuario == null) {
            return new CargaUsuario(
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

    private void VistaInit() {
        this.panelContenedorMenu1.Load(controladorForm);
        if (!navegador.ComprobacionGITVersion()) {
            String mensaje = "Esta aplicación requiere forzosamente instalada la extensión GIT\\n"
                    + "favor de buscar la siguiente URL https://git-scm.com/install/";
            JOptionPane.showMessageDialog(this, mensaje, "Instalador Git no instalado", JOptionPane.INFORMATION_MESSAGE);
            validarOperaciones = false;
        } else {
            validarOperaciones = true;
        }
        if (validarOperaciones) {
            List<String> rutasFisicasGitLocal = Lists.newArrayList();
            String ruta = navegador.RutaFisica(this);
            if (!Strings.isNullOrEmpty(ruta)) {
                rutasFisicasGitLocal.add(ruta);
            }
            rutasFisicasGitLocal.add(ruta);
            SeleccionRepositorioForm vistaForm = SeleccionRepositorioForm.builder()
                    .rutasRepositorios(rutasFisicasGitLocal)
                    .rutaActual(ruta)
                    .build();
            this.vistaRepo = vistaForm;
        } else {
            SeleccionRepositorioForm vistaForm = SeleccionRepositorioForm.builder()
                    .rutasRepositorios(Lists.newArrayList())
                    .rutaActual("")
                    .build();
            this.vistaRepo = vistaForm;
        }
        this.panelContenedorMenu1.Load(controladorForm, vistaRepo);
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
        getContentPane().add(panelContenedorMenu1, java.awt.BorderLayout.CENTER);

        SeccionRama.setText("Archivos");

        itemCrearRepositorio.setText("Generar Repositorio...");
        SeccionRama.add(itemCrearRepositorio);

        itemSeleccionarRepositorio.setText("Agregar Repositorio");
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
        ModalTokenUrl modalToken = new ModalTokenUrl(this, true);
        modalToken.setActivarUrlRemoto(vistaRepo.isActivarDireccionUrl());
        CenterInfoModal(modalToken);
    }//GEN-LAST:event_ItemTokenActionPerformed

    private void ItemUrlRemotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemUrlRemotoActionPerformed
        String urlRemoto = JOptionPane.showInputDialog(this, "Ingrese la url de su repositorio Remoto que le proporciona GitHub de este repositorio local", "Repositorio GitHub", JOptionPane.INFORMATION_MESSAGE);
        manejoUsuario.ObtenerTareaPrincipal(!Strings.isNullOrEmpty(urlRemoto)).thenAccept((resultado) -> {
            vistaRepo = resultado;
            vistaRepo.setRamasResult(resultado.getRamasResult());
            vistaRepo.setRemotosResult(resultado.getRemotosResult());
            vistaRepo.setStashesResult(resultado.getStashesResult());
            vistaRepo.setDtoResultado(resultado.getDtoResultado());
            vistaRepo.setRemotosUrl(resultado.getRemotosUrl());
            vistaRepo.setActivarDireccionUrl(resultado.getRemotosUrl().size() == 0);
            ModalTokenUrl modalToken = new ModalTokenUrl(this, true);
            modalToken.setActivarUrlRemoto(vistaRepo.isActivarDireccionUrl());
            CenterInfoModal(modalToken);
        });
    }//GEN-LAST:event_ItemUrlRemotoActionPerformed

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
    // End of variables declaration//GEN-END:variables
}
