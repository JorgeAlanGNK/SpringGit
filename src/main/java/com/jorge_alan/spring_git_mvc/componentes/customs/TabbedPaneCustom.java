package com.jorge_alan.spring_git_mvc.componentes.customs;

import javax.swing.JTabbedPane;
import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.ImagenEstatica;
import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstanteIcono;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class TabbedPaneCustom extends JTabbedPane {

    private ImagenEstatica imagenComponente = new ImagenEstatica();
    private DesignTab basicoDesign = new DesignTab();

    public TabbedPaneCustom() {
        setOpaque(true);
        setUI(basicoDesign);
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);
        CeldaPersonalizada personalizacion = new CeldaPersonalizada();
        personalizacion.setIconClose(imagenComponente.GenerarIcono(ConstanteIcono.ICONO_AGREGAR_TAB, 20, 20));
        personalizacion.setTitle(title);
        //escoger el indice actual para la pestaña del tab
        int index = getTabCount() - 1;
        personalizacion.setSelected(index == getSelectedIndex());
        setTabComponentAt(getTabCount() - 1, personalizacion);
    }

    private static final class DesignTab extends BasicTabbedPaneUI {

        public DesignTab() {
        }

        @Override
        protected void paintFocusIndicator(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect, boolean isSelected) {
        }

        @Override
        protected void paintTabBackground(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
        }

        @Override
        protected void paintTabBorder(Graphics g, int tabPlacement, int tabIndex, int x, int y, int w, int h, boolean isSelected) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (isSelected) {
                g.setColor(new Color(255, 67, 28));
                g.fillRect(x, y + h - 5, w, 3);
            }
        }

    }

}
