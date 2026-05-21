package com.jorge_alan.spring_git_mvc.componentes.customs;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonEmpty extends JButton {

    private Font texto;
    private String nameFont = "Arial";
    private int fontType = Font.PLAIN;
    private int sizeFont = 12;
    private boolean activarFontCustom;

    public ButtonEmpty() {
        setOpaque(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setBorder(new EmptyBorder(5, 15, 5, 15));
        InitTexto();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Rectangle2D shape = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2d.setBackground(getParent().getBackground());
        g2d.fill(shape);
        super.paintComponent(g);
    }

    private void InitTexto() {
        this.texto = new Font(nameFont, fontType, sizeFont);
        Map attributes = this.texto.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        setFont(this.texto.deriveFont(attributes));
    }

}
