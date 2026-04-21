package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstruccionNavegador;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.GitVisualizacion;
import com.jorge_alan.spring_git_mvc.modelos.CapaModeloNegocio.VisualizacionModelo;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.SeleccionRepositorioForm;
import com.jorge_alan.spring_git_mvc.negocios.CapaNegocio.IVisualizacionNegocio;
import com.jorge_alan.spring_git_mvc.negocios.CapaNegocio.VisualizacionRama;

import java.awt.Toolkit;
import java.awt.Dimension;
import java.util.List;
import javax.swing.JOptionPane;
import lombok.Getter;
import lombok.Setter;

public class FormApp extends javax.swing.JFrame {

    private static final Dimension winDim = Toolkit.getDefaultToolkit().getScreenSize();
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormApp.class.getName());
    private static IVisualizacionNegocio _negocio = InitVisualizacion();
    private static ConstruccionNavegador navegador = IniciarNavegador();
    private SeleccionRepositorioForm vistaRepo;
    private boolean validarOperaciones;

    private static VisualizacionModelo visualizacion = IniciarVisualizacion();

    private static IVisualizacionNegocio InitVisualizacion() {
        if (_negocio == null) {
            return _negocio = new VisualizacionRama(new GitVisualizacion());
        }
        return _negocio;
    }

    private static VisualizacionModelo IniciarVisualizacion() {
        if (visualizacion == null) {
            return new VisualizacionModelo();
        }
        return visualizacion;
    }

    private static ConstruccionNavegador IniciarNavegador() {
        if (navegador == null) {
            return new ConstruccionNavegador();
        }
        return navegador;
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
        this.panelContenedorMenu1.load(_negocio, vistaRepo);
    }

    public FormApp() {
        setVisible(true);
        LookAndFeel();
        initComponents();
        VistaInit();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContenedorMenu1 = new com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu();
        accesoOperacion = new javax.swing.JMenuBar();
        SeccionRama = new javax.swing.JMenu();
        itemCrearRepositorio = new javax.swing.JMenuItem();
        itemSeleccionarRepositorio = new javax.swing.JMenuItem();

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

        setJMenuBar(accesoOperacion);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
    private javax.swing.JMenu SeccionRama;
    private javax.swing.JMenuBar accesoOperacion;
    private javax.swing.JMenuItem itemCrearRepositorio;
    private javax.swing.JMenuItem itemSeleccionarRepositorio;
    private com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu panelContenedorMenu1;
    // End of variables declaration//GEN-END:variables
}
