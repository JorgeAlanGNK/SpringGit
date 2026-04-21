package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstanteIcono;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.IGitVisualizacion;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.GitVisualizacion;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.SeleccionRepositorioForm;
import com.jorge_alan.spring_git_mvc.negocios.CapaNegocio.VisualizacionRama;
import com.jorge_alan.spring_git_mvc.negocios.CapaNegocio.IVisualizacionNegocio;
import com.kitfox.svg.app.beans.SVGIcon;
import java.awt.Dimension;
import java.util.List;
import javax.swing.Icon;
import lombok.Getter;
import lombok.Setter;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;

public class PanelContenedorMenu extends javax.swing.JPanel {

    private boolean toggleRama;
    private boolean toggleRemoto;
    private boolean toggleStash;
    private Icon ICON_BRANCH_SIGNAL;
    private Icon ICON_REMOTE_SIGNAL;
    private Icon ICON_STASHES_SIGNAL;
    private Icon ICON_DOWN_SIGNAL;
    private Icon ICON_UP_SIGNAL;    
    private IVisualizacionNegocio _comandoVisualizacion;
    private SeleccionRepositorioForm vista;

    public PanelContenedorMenu() {
        initIcons();
        initComponents();
    }
    
    public void load(IVisualizacionNegocio comandoVisualizacion, SeleccionRepositorioForm vista) {
        this._comandoVisualizacion = comandoVisualizacion;
        this.vista = vista;
        ExecuteVisual();
    }
    

    private void initIcons() {
        this.ICON_BRANCH_SIGNAL = CargaIcono(ConstanteIcono.ICONO_RAMA, 20, 20);
        this.ICON_REMOTE_SIGNAL = CargaIcono(ConstanteIcono.ICONO_REMOTO, 20, 20);
        this.ICON_STASHES_SIGNAL = CargaIcono(ConstanteIcono.ICONO_STASH, 20, 20);
        this.ICON_DOWN_SIGNAL = CargaIcono(ConstanteIcono.ICONO_BTN_DOWN, 20, 20);
        this.ICON_UP_SIGNAL = CargaIcono(ConstanteIcono.ICONO_BTN_UP, 20, 20);
    }

    private Icon CargaIcono(String nombreIcono, int width, int heigth) {
        try {
            SVGIcon icon = new SVGIcon();
            icon.setSvgURI(PanelContenedorMenu.class.getClassLoader().getResource(nombreIcono).toURI());
            icon.setScaleToFit(true);
            icon.setPreferredSize(new Dimension(width, heigth));
            return icon;
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    private void ExecuteVisual() {
        this._comandoVisualizacion.RamasRemotas(this.vista.getRutaActual()).thenApply((result) -> {
            return SeleccionRepositorioForm.builder()
                    .ramasResult(result.getRamas())
                    .stashesResult(result.getStashes())
                    .build();
        }).thenAccept((v) -> {
            List<RamaModelo> ramas = v.getRamasResult();
            List<StashModelo> stashes = v.getStashesResult();
            List<RamaModelo> remotos = v.getRemotosResult();
            this.vista.setRamasResult(ramas);
            this.vista.setRemotosResult(remotos);
            this.vista.setStashesResult(stashes);
        });
    }

    private void TogglePanel(javax.swing.JPanel toggleCmp, javax.swing.JPanel parent, javax.swing.JButton btnAction, boolean isCollapse) {
        if (isCollapse) {
            btnAction.setIcon(ICON_UP_SIGNAL);
        } else {
            btnAction.setIcon(ICON_DOWN_SIGNAL);
        }
        toggleCmp.setVisible(isCollapse);
        parent.revalidate();
        parent.repaint();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        barra = new javax.swing.JPanel();
        ramasPanel = new javax.swing.JPanel();
        areaRama = new javax.swing.JPanel();
        accionRama = new javax.swing.JButton();
        campoRama = new javax.swing.JTextField();
        ramaTitulo = new javax.swing.JLabel();
        ramaIcono = new javax.swing.JLabel();
        collapseRama = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        remotosPanel = new javax.swing.JPanel();
        areaRemoto = new javax.swing.JPanel();
        accionRemoto = new javax.swing.JButton();
        campoRemoto = new javax.swing.JTextField();
        remotoTitulo = new javax.swing.JLabel();
        remotoIcono = new javax.swing.JLabel();
        collapseRemoto = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        stashesPanel = new javax.swing.JPanel();
        areaStash = new javax.swing.JPanel();
        accionStash = new javax.swing.JButton();
        campoStash = new javax.swing.JTextField();
        stashTitulo = new javax.swing.JLabel();
        stashIcono = new javax.swing.JLabel();
        collapseStash = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        contenido = new javax.swing.JPanel();

        setLayout(new java.awt.GridBagLayout());

        barra.setBackground(new java.awt.Color(255, 0, 0));
        barra.setLayout(new java.awt.GridBagLayout());

        ramasPanel.setLayout(new javax.swing.BoxLayout(ramasPanel, javax.swing.BoxLayout.Y_AXIS));

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 7, 7);
        flowLayout1.setAlignOnBaseline(true);
        areaRama.setLayout(flowLayout1);

        accionRama.setIcon(ICON_DOWN_SIGNAL);
        accionRama.addActionListener(this::accionRamaActionPerformed);
        areaRama.add(accionRama);

        campoRama.setText("buscar...");
        areaRama.add(campoRama);

        ramaTitulo.setText("RAMAS");
        areaRama.add(ramaTitulo);

        ramaIcono.setIcon(ICON_BRANCH_SIGNAL);
        areaRama.add(ramaIcono);

        ramasPanel.add(areaRama);

        collapseRama.setVisible(false);

        jLabel1.setText("jLabel1");
        collapseRama.add(jLabel1);

        ramasPanel.add(collapseRama);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        barra.add(ramasPanel, gridBagConstraints);

        remotosPanel.setLayout(new javax.swing.BoxLayout(remotosPanel, javax.swing.BoxLayout.Y_AXIS));

        java.awt.FlowLayout flowLayout2 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 7, 7);
        flowLayout2.setAlignOnBaseline(true);
        areaRemoto.setLayout(flowLayout2);

        accionRemoto.setIcon(ICON_DOWN_SIGNAL);
        accionRemoto.addActionListener(this::accionRemotoActionPerformed);
        areaRemoto.add(accionRemoto);

        campoRemoto.setText("buscar...");
        areaRemoto.add(campoRemoto);

        remotoTitulo.setText("REMOTOS");
        areaRemoto.add(remotoTitulo);

        remotoIcono.setIcon(ICON_REMOTE_SIGNAL);
        areaRemoto.add(remotoIcono);

        remotosPanel.add(areaRemoto);

        collapseRemoto.setVisible(false);

        jLabel2.setText("jLabel2");
        collapseRemoto.add(jLabel2);

        remotosPanel.add(collapseRemoto);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        barra.add(remotosPanel, gridBagConstraints);

        stashesPanel.setLayout(new javax.swing.BoxLayout(stashesPanel, javax.swing.BoxLayout.Y_AXIS));

        java.awt.FlowLayout flowLayout3 = new java.awt.FlowLayout(java.awt.FlowLayout.LEADING, 7, 7);
        flowLayout3.setAlignOnBaseline(true);
        areaStash.setLayout(flowLayout3);

        accionStash.setIcon(ICON_DOWN_SIGNAL);
        accionStash.addActionListener(this::accionStashActionPerformed);
        areaStash.add(accionStash);

        campoStash.setText("buscar...");
        areaStash.add(campoStash);

        stashTitulo.setText("STASHES");
        areaStash.add(stashTitulo);

        stashIcono.setIcon(ICON_STASHES_SIGNAL);
        areaStash.add(stashIcono);

        stashesPanel.add(areaStash);

        collapseStash.setVisible(false);

        jLabel3.setText("jLabel3");
        collapseStash.add(jLabel3);

        stashesPanel.add(collapseStash);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        barra.add(stashesPanel, gridBagConstraints);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.2;
        gridBagConstraints.weighty = 1.0;
        add(barra, gridBagConstraints);

        contenido.setBackground(new java.awt.Color(0, 0, 255));

        javax.swing.GroupLayout contenidoLayout = new javax.swing.GroupLayout(contenido);
        contenido.setLayout(contenidoLayout);
        contenidoLayout.setHorizontalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 489, Short.MAX_VALUE)
        );
        contenidoLayout.setVerticalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 261, Short.MAX_VALUE)
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        add(contenido, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void accionRamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionRamaActionPerformed
        boolean cambiador = !this.toggleRama;
        javax.swing.JPanel collapse = this.collapseRama;
        javax.swing.JPanel parent = this.ramasPanel;
        javax.swing.JButton accionador = this.accionRama;
        TogglePanel(collapse, parent, accionador, cambiador);
        this.toggleRama = cambiador;
    }//GEN-LAST:event_accionRamaActionPerformed

    private void accionRemotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionRemotoActionPerformed
        boolean cambiador = !this.toggleRemoto;
        javax.swing.JPanel collapse = this.collapseRemoto;
        javax.swing.JPanel parent = this.remotosPanel;
        javax.swing.JButton accionador = this.accionRemoto;
        TogglePanel(collapse, parent, accionador, cambiador);
        this.toggleRemoto = cambiador;
    }//GEN-LAST:event_accionRemotoActionPerformed

    private void accionStashActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accionStashActionPerformed
        boolean cambiador = !this.toggleStash;
        javax.swing.JPanel collapse = this.collapseStash;
        javax.swing.JPanel parent = this.stashesPanel;
        javax.swing.JButton accionador = this.accionStash;
        TogglePanel(collapse, parent, accionador, cambiador);
        this.toggleStash = cambiador;
    }//GEN-LAST:event_accionStashActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton accionRama;
    private javax.swing.JButton accionRemoto;
    private javax.swing.JButton accionStash;
    private javax.swing.JPanel areaRama;
    private javax.swing.JPanel areaRemoto;
    private javax.swing.JPanel areaStash;
    private javax.swing.JPanel barra;
    private javax.swing.JTextField campoRama;
    private javax.swing.JTextField campoRemoto;
    private javax.swing.JTextField campoStash;
    private javax.swing.JPanel collapseRama;
    private javax.swing.JPanel collapseRemoto;
    private javax.swing.JPanel collapseStash;
    private javax.swing.JPanel contenido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel ramaIcono;
    private javax.swing.JLabel ramaTitulo;
    private javax.swing.JPanel ramasPanel;
    private javax.swing.JLabel remotoIcono;
    private javax.swing.JLabel remotoTitulo;
    private javax.swing.JPanel remotosPanel;
    private javax.swing.JLabel stashIcono;
    private javax.swing.JLabel stashTitulo;
    private javax.swing.JPanel stashesPanel;
    // End of variables declaration//GEN-END:variables
}
