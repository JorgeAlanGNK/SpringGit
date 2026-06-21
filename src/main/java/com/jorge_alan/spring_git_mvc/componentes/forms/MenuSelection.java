package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstruccionNavegador;

public class MenuSelection extends javax.swing.JPanel {

    private ConstruccionNavegador navegador;

    public MenuSelection() {
        initComponents();
    }

    //propiedades
    public void setNavegador(ConstruccionNavegador navegador) {
        this.navegador = navegador;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelContainer = new javax.swing.JPanel();
        btnEscogerRepositorio1 = new com.jorge_alan.spring_git_mvc.componentes.customs.ButtonEmpty();
        btnGemerarRepositoro = new com.jorge_alan.spring_git_mvc.componentes.customs.ButtonEmpty();
        btnToken = new com.jorge_alan.spring_git_mvc.componentes.customs.ButtonEmpty();

        btnEscogerRepositorio1.setForeground(new java.awt.Color(0, 0, 0));
        btnEscogerRepositorio1.setText("Seleccionar un Repositorio... CTRL+N");
        btnEscogerRepositorio1.addActionListener(this::btnEscogerRepositorio1ActionPerformed);

        btnGemerarRepositoro.setForeground(new java.awt.Color(0, 0, 0));
        btnGemerarRepositoro.setText("Generar un Repositorio... CTRL+O");

        btnToken.setForeground(new java.awt.Color(0, 0, 0));
        btnToken.setText("Agregar token... CTRL+SHIFT+N");

        javax.swing.GroupLayout panelContainerLayout = new javax.swing.GroupLayout(panelContainer);
        panelContainer.setLayout(panelContainerLayout);
        panelContainerLayout.setHorizontalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelContainerLayout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnToken, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                    .addGroup(panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnEscogerRepositorio1, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                        .addComponent(btnGemerarRepositoro, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(84, Short.MAX_VALUE))
        );
        panelContainerLayout.setVerticalGroup(
            panelContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelContainerLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnEscogerRepositorio1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGemerarRepositoro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnToken, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(163, Short.MAX_VALUE)
                .addComponent(panelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(panelContainer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(68, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEscogerRepositorio1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEscogerRepositorio1ActionPerformed
        //se necesita generar un archivo para escoger un repositorio
        
    }//GEN-LAST:event_btnEscogerRepositorio1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.jorge_alan.spring_git_mvc.componentes.customs.ButtonEmpty btnEscogerRepositorio1;
    private com.jorge_alan.spring_git_mvc.componentes.customs.ButtonEmpty btnGemerarRepositoro;
    private com.jorge_alan.spring_git_mvc.componentes.customs.ButtonEmpty btnToken;
    private javax.swing.JPanel panelContainer;
    // End of variables declaration//GEN-END:variables
}
