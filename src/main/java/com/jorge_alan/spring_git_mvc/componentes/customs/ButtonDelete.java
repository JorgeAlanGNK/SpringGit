package com.jorge_alan.spring_git_mvc.componentes.customs;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.EmptyBorder;
import java.awt.LinearGradientPaint;

public class ButtonDelete extends JButton {

    //colores para el boton principal
    private Color firstColorPrimary = new Color(255, 67, 28);
    private Color secondColorPrimary = new Color(147, 41, 30);
    private GradientPaint paintGradientPrimary;
    //colores para el click
    private Color colorClick;
    //colores para el hover
    private Color colorHover;
    private int posXColorStart;
    private int posYColorStart;
    private int posXColorEnd;
    private int posYColorEnd;
    private int radius;
    private int minusWidth;
    private int minusHeight;

    public ButtonDelete() {
        setContentAreaFilled(false);
        setBorderPainted(false);
        setOpaque(false);
        this.paintGradientPrimary = gradientPaintSource();
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }
        });
    }

    private GradientPaint gradientPaintSource() {
        int endX = Math.max(0, getWidth() - this.minusWidth);
        int endY = Math.max(0, getHeight() - this.minusHeight);
        return new GradientPaint(this.posXColorStart, this.posXColorEnd, this.firstColorPrimary, endX, endY, this.secondColorPrimary);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.paintGradientPrimary = gradientPaintSource();
        RoundRectangle2D roundArea = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), this.radius, this.radius);
        g2d.setPaint(this.paintGradientPrimary);
        g2d.fill(roundArea);
        super.paintComponent(g);
    }

    //configuraciones personalizadas
    public Color getFirstColorPrimary() {
        return firstColorPrimary;
    }

    public void setFirstColorPrimary(Color firstColorPrimary) {
        this.firstColorPrimary = firstColorPrimary;
    }

    public Color getSecondColorPrimary() {
        return secondColorPrimary;
    }

    public void setSecondColorPrimary(Color secondColorPrimary) {
        this.secondColorPrimary = secondColorPrimary;
    }

    public GradientPaint getPaintGradientPrimary() {
        return paintGradientPrimary;
    }

    public void setPaintGradientPrimary(GradientPaint paintGradientPrimary) {
        this.paintGradientPrimary = paintGradientPrimary;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public int getMinusWidth() {
        return minusWidth;
    }

    public void setMinusWidth(int minusWidth) {
        this.minusWidth = minusWidth;
    }

    public int getMinusHeight() {
        return minusHeight;
    }

    public void setMinusHeight(int minusHeight) {
        this.minusHeight = minusHeight;
    }

    public int getPosXColorStart() {
        return posXColorStart;
    }

    public void setPosXColorStart(int posXColorStart) {
        this.posXColorStart = posXColorStart;
    }

    public int getPosYColorStart() {
        return posYColorStart;
    }

    public void setPosYColorStart(int posYColorStart) {
        this.posYColorStart = posYColorStart;
    }

    public int getPosXColorEnd() {
        return posXColorEnd;
    }

    public void setPosXColorEnd(int posXColorEnd) {
        this.posXColorEnd = posXColorEnd;
    }

    public int getPosYColorEnd() {
        return posYColorEnd;
    }

    public void setPosYColorEnd(int posYColorEnd) {
        this.posYColorEnd = posYColorEnd;
    }

}
