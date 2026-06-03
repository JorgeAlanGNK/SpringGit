package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstanteIcono;
import com.jorge_alan.spring_git_mvc.componentes.extension.ImagenEstatica;

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
        formatDate = new javax.swing.JFormattedTextField();
        descGeneral = new javax.swing.JLabel();
        descUrlRemoto = new javax.swing.JLabel();
        campoUrlRemoto = new com.jorge_alan.spring_git_mvc.componentes.customs.CampoEdicion();
        campoEdicion1 = new com.jorge_alan.spring_git_mvc.componentes.customs.CampoEdicion();
        campoEdicion2 = new com.jorge_alan.spring_git_mvc.componentes.customs.CampoEdicion();

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

        campoUrlRemoto.setText("campoEdicion1");

        campoEdicion1.setText("campoEdicion1");

        campoEdicion2.setText("campoEdicion2");

        javax.swing.GroupLayout panelTokenLayout = new javax.swing.GroupLayout(panelToken);
        panelToken.setLayout(panelTokenLayout);
        panelTokenLayout.setHorizontalGroup(
            panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTokenLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTokenLayout.createSequentialGroup()
                        .addComponent(btnAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCancelar)
                        .addGap(68, 68, 68))
                    .addGroup(panelTokenLayout.createSequentialGroup()
                        .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(panelTokenLayout.createSequentialGroup()
                                    .addComponent(descUrlRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(campoUrlRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, panelTokenLayout.createSequentialGroup()
                                    .addComponent(fechaDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(formatDate, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(panelTokenLayout.createSequentialGroup()
                                .addComponent(descGeneral, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(27, 27, 27)
                                .addComponent(campoEdicion1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(panelTokenLayout.createSequentialGroup()
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(panelTokenLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(descToken, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(campoEdicion2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(panelTokenLayout.createSequentialGroup()
                            .addGap(20, 20, 20)
                            .addComponent(etiquetaDescripcion))
                        .addGroup(panelTokenLayout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(activarTokenCheck))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        panelTokenLayout.setVerticalGroup(
            panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTokenLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(etiquetaDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(campoEdicion2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(descToken, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechaDesc)
                    .addComponent(formatDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descGeneral)
                    .addComponent(campoEdicion1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descUrlRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(campoUrlRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addComponent(activarTokenCheck)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelTokenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAceptar)
                    .addComponent(btnCancelar))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(273, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelToken, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(areaLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(187, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(areaLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelToken, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                .addContainerGap())
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
    private com.jorge_alan.spring_git_mvc.componentes.customs.CampoEdicion campoEdicion1;
    private com.jorge_alan.spring_git_mvc.componentes.customs.CampoEdicion campoEdicion2;
    private com.jorge_alan.spring_git_mvc.componentes.customs.CampoEdicion campoUrlRemoto;
    private javax.swing.JLabel descGeneral;
    private javax.swing.JLabel descToken;
    private javax.swing.JLabel descUrlRemoto;
    private javax.swing.JLabel etiquetaDescripcion;
    private javax.swing.JLabel fechaDesc;
    private javax.swing.JFormattedTextField formatDate;
    private javax.swing.JLabel iconoGit;
    private javax.swing.JPanel panelToken;
    // End of variables declaration//GEN-END:variables
}
