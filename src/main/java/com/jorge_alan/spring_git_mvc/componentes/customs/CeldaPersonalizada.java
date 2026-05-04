package com.jorge_alan.spring_git_mvc.componentes.customs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.HashMap;
import java.util.Map;
import javax.swing.Icon;

public class CeldaPersonalizada extends javax.swing.JPanel {
//celda personalizada para un JTree
    private Icon representacionIcono;
    private String nombreRama;

    public CeldaPersonalizada() {//no tocar
        initComponents();
    }

    public String getNombreRama() {
        return nombreRama;
    }

    public void setNombreRama(String nombreRama) {
        //sirve para cambiar el valor, sin utiilzar eventos
        double longitudRama = nombreRama.length() * 10;
        this.nombreRama = nombreRama;
        this.textoValor.setText(nombreRama);
        Dimension dimLabel = this.textoValor.getPreferredSize();
        dimLabel.setSize(longitudRama, dimLabel.getHeight());
        this.textoValor.setPreferredSize(dimLabel);
        //identifica el problema del label si aparecen ...
//        this.textoValor.setBorder(javax.swing.BorderFactory.createLineBorder(Color.RED));
    }

    public void setLeafIcon(Icon leafIcon) {
        this.representacionIcono = leafIcon;
        this.iconoLeaf.setIcon(leafIcon);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconoLeaf = new javax.swing.JLabel();
        textoValor = new javax.swing.JLabel();

        setBackground(new java.awt.Color(36, 161, 197));

        iconoLeaf.setForeground(new java.awt.Color(0, 0, 0));

        textoValor.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(iconoLeaf, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(textoValor, javax.swing.GroupLayout.DEFAULT_SIZE, 128, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoLeaf, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoValor, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel iconoLeaf;
    private javax.swing.JLabel textoValor;
    // End of variables declaration//GEN-END:variables
}
