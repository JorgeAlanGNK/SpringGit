package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.ImagenEstatica;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstanteIcono;
import java.awt.Dimension;

public class ModalTokenUrl extends javax.swing.JDialog {
    
    private ImagenEstatica extImagenes = new ImagenEstatica();
    private boolean activarUrlRemoto;

    public ModalTokenUrl(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        CargarIconos();
    }
    
    private void CargarIconos() {
        iconoGit.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_GIT_LOGO, 120, 120));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        areaLogo = new javax.swing.JPanel();
        iconoGit = new javax.swing.JLabel();
        panelToken = new javax.swing.JPanel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        etiquetaDescripcion = new javax.swing.JLabel();
        activarTokenCheck = new javax.swing.JCheckBox();
        descToken = new javax.swing.JLabel();
        fechaDesc = new javax.swing.JLabel();
        tokenPathGit = new javax.swing.JPasswordField();
        formatDate = new javax.swing.JFormattedTextField();
        descGeneral = new javax.swing.JLabel();
        campoDescGeneral = new javax.swing.JTextField();
        descUrlRemoto = new javax.swing.JLabel();
        campoUrlRemoto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout();
        flowLayout1.setAlignOnBaseline(true);
        areaLogo.setLayout(flowLayout1);
        areaLogo.add(iconoGit);

        panelToken.setPreferredSize(new java.awt.Dimension(360, 100));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(this::btnCancelarActionPerformed);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(this::btnAceptarActionPerformed);

        etiquetaDescripcion.setText("Ingrese el path que desea guardar para el acceso a tu repositorio");

        activarTokenCheck.setText("Utilizar Token");

        descToken.setText("Token:");

        fechaDesc.setText("Fecha de caducidad:");

        formatDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter(new java.text.SimpleDateFormat("dd/MM/yyyy"))));
        formatDate.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        formatDate.setText("dd/MM/yyyy");
        formatDate.addActionListener(this::formatDateActionPerformed);

        descGeneral.setText("Descripcion:");

        descUrlRemoto.setText("Url Remoto:");
        descUrlRemoto.setVisible(activarUrlRemoto);

        campoUrlRemoto.setVisible(activarUrlRemoto);

        javax.swing.GroupLayout panelTokenLayout = new javax.swing.GroupLayout(panelToken);
        panelToken.setLayout(panelTokenLayout);
        panelTokenLayout.setHorizontalGroup(
            panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTokenLayout.createSequentialGroup()
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTokenLayout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(etiquetaDescripcion))
                    .addGroup(panelTokenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(activarTokenCheck)))
                .addContainerGap(47, Short.MAX_VALUE))
            .addGroup(panelTokenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelTokenLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(73, 73, 73))
                    .addGroup(panelTokenLayout.createSequentialGroup()
                        .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(panelTokenLayout.createSequentialGroup()
                                .addComponent(fechaDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(formatDate, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTokenLayout.createSequentialGroup()
                                .addComponent(descToken, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(81, 81, 81)
                                .addComponent(tokenPathGit, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(panelTokenLayout.createSequentialGroup()
                                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(descUrlRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(40, 40, 40)
                                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(campoDescGeneral)
                                    .addComponent(campoUrlRemoto))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        panelTokenLayout.setVerticalGroup(
            panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTokenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descToken)
                    .addComponent(tokenPathGit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaDesc)
                    .addComponent(formatDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descGeneral)
                    .addComponent(campoDescGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoUrlRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descUrlRemoto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(activarTokenCheck)
                .addGap(18, 18, 18)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnCancelar)
                    .addComponent(btnAceptar))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(239, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelToken, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(areaLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(150, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(areaLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelToken, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        //se toma todos los valores
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void formatDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_formatDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_formatDateActionPerformed

    public boolean isActivarUrlRemoto() {
        return activarUrlRemoto;
    }

    public void setActivarUrlRemoto(boolean activarUrlRemoto) {
        this.activarUrlRemoto = activarUrlRemoto;
        this.descUrlRemoto.setVisible(!activarUrlRemoto);
        this.campoUrlRemoto.setVisible(!activarUrlRemoto);
        revalidate();
        repaint();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox activarTokenCheck;
    private javax.swing.JPanel areaLogo;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JTextField campoDescGeneral;
    private javax.swing.JTextField campoUrlRemoto;
    private javax.swing.JLabel descGeneral;
    private javax.swing.JLabel descToken;
    private javax.swing.JLabel descUrlRemoto;
    private javax.swing.JLabel etiquetaDescripcion;
    private javax.swing.JLabel fechaDesc;
    private javax.swing.JFormattedTextField formatDate;
    private javax.swing.JLabel iconoGit;
    private javax.swing.JPanel panelToken;
    private javax.swing.JPasswordField tokenPathGit;
    // End of variables declaration//GEN-END:variables
}
