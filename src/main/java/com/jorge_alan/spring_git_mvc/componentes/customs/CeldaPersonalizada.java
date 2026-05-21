package com.jorge_alan.spring_git_mvc.componentes.customs;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.Icon;

import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstruccionNavegador;
public class CeldaPersonalizada extends javax.swing.JPanel {
//celda personalizada para un JTabbedPane
    //default Color show

    private Color ColorFirst = new Color(28, 181, 224);
    private Color ColorSecond = new Color(0, 8, 81);
    //selection TabColor
    private Color ColorSelectionFirst = new Color(0, 97, 255);
    private Color ColorSelectionSecond = new Color(96, 239, 255);
    private ConstruccionNavegador navegador;

    private Icon representacionIcono;
    private boolean isSelected;

    public CeldaPersonalizada() {//no tocar, netbeans lo detecta como default
        initComponents();
    }
    
    public void setNavegador(ConstruccionNavegador navegador) {
        this.navegador = navegador;
    }

    public void setTitle(String text) {
        this.iconoLeaf.setText(text);
    }

    public void setIconClose(Icon icon) {
        this.iconoCerrar.setIcon(icon);
    }
    
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        RoundRectangle2D rect = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20);
        GradientPaint selection = GradientSelection(0, 0, getWidth(), getHeight(), isSelected);
        g2d.setPaint(selection);
        g2d.fill(rect);
    }
    
    public void EsSeleccionado (boolean isSelected) {
        this.isSelected = isSelected;
    }

    private GradientPaint GradientSelection(int x, int y, int w, int h, boolean selected) {
        if (selected) {
            return new GradientPaint(x, y, ColorSelectionFirst, w, y + h, ColorSelectionSecond);
        } else {
            return new GradientPaint(x, y, ColorFirst, w, y + h, ColorSecond);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        iconoLeaf = new javax.swing.JLabel();
        iconoCerrar = new javax.swing.JButton();

        setBackground(new java.awt.Color(36, 161, 197));

        iconoLeaf.setForeground(new java.awt.Color(0, 0, 0));
        iconoLeaf.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        iconoCerrar.addActionListener(this::iconoCerrarActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(iconoLeaf, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iconoLeaf, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
                    .addComponent(iconoCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void iconoCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iconoCerrarActionPerformed
        
    }//GEN-LAST:event_iconoCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton iconoCerrar;
    private javax.swing.JLabel iconoLeaf;
    // End of variables declaration//GEN-END:variables
}
