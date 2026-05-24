package com.jorge_alan.spring_git_mvc.componentes.customs;

import javax.swing.JTabbedPane;
import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.ImagenEstatica;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstanteIcono;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstruccionNavegador;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class TabbedPaneCustom extends JTabbedPane {

    private ImagenEstatica imagenComponente = new ImagenEstatica();
    private DesignTab basicoDesign = new DesignTab();
    private ConstruccionNavegador navegador;

    public TabbedPaneCustom() {
        setOpaque(true);
        setUI(basicoDesign);
    }

    public void setNavegador(ConstruccionNavegador navegador) {
        this.navegador = navegador;
    }

    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);
        int index = getTabCount() - 1;
        CeldaPersonalizada personalizacion = new CeldaPersonalizada();
        personalizacion.setNavegador(navegador);
        personalizacion.setIconClose(imagenComponente.GenerarIcono(ConstanteIcono.ICONO_AGREGAR_TAB, 20, 20));
        personalizacion.setTitle(title);
        setTabComponentAt(index, personalizacion);
        Component tabComponent = getTabComponentAt(index);
        personalizacion.setBounds(0, 0, this.basicoDesign.getWidthRect(), this.basicoDesign.getHeigthRect());
        //escoger el indice actual para la pestaña del tab
        personalizacion.setSelected(index == getSelectedIndex());
    }

    private static final class DesignTab extends BasicTabbedPaneUI {
        
        private int widthRect;
        private int heigthRect;

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
            this.widthRect = w;
            this.heigthRect = h;
            if (isSelected) {
                g.setColor(new Color(255, 67, 28));
                g.fillRect(x, y + h - 5, w, 3);
            }
        }

        public int getWidthRect() {
            return widthRect;
        }

        public int getHeigthRect() {
            return heigthRect;
        }

    }

}
