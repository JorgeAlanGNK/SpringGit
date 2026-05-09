package com.jorge_alan.spring_git_mvc.componentes.customs;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import javax.swing.JTabbedPane;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.Image;

import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.ImagenEstatica;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstanteIcono;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;
import javax.swing.Icon;
import javax.swing.text.View;

public class TabbedPaneCustom extends JTabbedPane {

    public TabbedPaneCustom() {
        setOpaque(true);
        setUI(new TabbedUI());
    }

    private static class TabbedUI extends BasicTabbedPaneUI {

        private ImagenEstatica imagenes = new ImagenEstatica();

        //default Color show
        private Color ColorFirst = new Color(28, 181, 224);
        private Color ColorSecond = new Color(0, 8, 81);
        //selection TabColor
        private Color ColorSelectionFirst = new Color(0, 97, 255);
        private Color ColorSelectionSecond = new Color(96, 239, 255);

        @Override
        protected void paintText(Graphics g, int tabPlacement, Font font, FontMetrics metrics, int tabIndex, String title, Rectangle textRect, boolean isSelected) {
            g.setColor(Color.WHITE);
            super.paintText(g, tabPlacement, font, metrics, tabIndex, title, textRect, isSelected); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        }

        @Override
        protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
            super.paintTab(g, tabPlacement, rects, tabIndex, iconRect, textRect);
            Graphics2D g2d = (Graphics2D) g.create();

            int valorRect = 20;
            Icon tabOptAdd = imagenes.GenerarIcono(ConstanteIcono.ICONO_AGREGAR_TAB, valorRect, valorRect);

            // 👉 Aquí usas la posición REAL del texto
            int iconX = textRect.x + valorRect + 10;
            int iconY = textRect.y + (textRect.height - valorRect) / 2;

            tabOptAdd.paintIcon(tabPane, g2d, iconX, iconY);

            g2d.dispose();
        }

        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        }

        @Override
        protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2d = (Graphics2D) g.create();
            Rectangle rec = getTabBounds(tabPane, tabIndex);//este es el tab actual
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gradient = GradientSelection(x, y, w, h, isSelected);
            g2d.setPaint(gradient);
            g2d.fillRoundRect(x, y, w, h, 5, 5);
            //generar el icono paintIcon
            int valorRect = 20;
            g2d.dispose();
        }

        @Override
        protected Insets getTabInsets(int tabPlacement, int tabIndex) {
            return new Insets(3, 30, 3, 30);
        }

        private GradientPaint GradientSelection(int x, int y, int w, int h, boolean selected) {
            if (selected) {
                return new GradientPaint(x, y, ColorSelectionFirst, w, y + h, ColorSelectionSecond);
            } else {
                return new GradientPaint(x, y, ColorFirst, w, y + h, ColorSecond);
            }
        }

    }

}
