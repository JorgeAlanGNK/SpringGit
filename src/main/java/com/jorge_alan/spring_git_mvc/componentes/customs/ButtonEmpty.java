package com.jorge_alan.spring_git_mvc.componentes.customs;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.font.TextAttribute;
import java.awt.geom.Rectangle2D;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class ButtonEmpty extends JButton {

    private Color colorHover = new Color(0, 204, 204);
    private Color colorNormal = new Color(0, 0, 0);

    public ButtonEmpty() {
        setBorderPainted(false);
        setContentAreaFilled(false);
        setOpaque(false);
        setBorder(new EmptyBorder(8, 8, 8, 8));
        setUnderline();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setForeground(colorHover);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setForeground(colorNormal);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Rectangle2D shape = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2d.clip(shape);
        super.paintComponent(g);
    }

    private void setUnderline() {
        Font font = getFont();
        Map<TextAttribute, Object> attributes = new HashMap<>(font.getAttributes());
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        Font nuevaFuente = font.deriveFont(attributes);
        setFont(nuevaFuente);
    }

    public Color getColorHover() {
        return colorHover;
    }

    public void setColorHover(Color colorHover) {
        this.colorHover = colorHover;
    }

    public Color getColorNormal() {
        return colorNormal;
    }

    public void setColorNormal(Color colorNormal) {
        this.colorNormal = colorNormal;
    }

}
