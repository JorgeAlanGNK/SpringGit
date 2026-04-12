package com.jorge_alan.spring_git_mvc.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.util.List;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.EstructuraPanel;
import com.kitfox.svg.app.beans.SVGIcon;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public final class Principal {

    @Builder
    @AllArgsConstructor
    public static final class BuildLayoutGlass implements IBuilder {

        private JFrame initClass;
        @Getter
        @Setter
        private List<EstructuraPanel> paneles;

        @Override
        public void Builder() {
            GridBagLayout grid = new GridBagLayout();
            GridBagConstraints grBag = new GridBagConstraints();
            Color red = new Color(255, 0, 0);
            Color blue = new Color(0, 0, 255);
            JPanel barra = new JPanel();
            barra.setBackground(red);
            JPanel contenido = new JPanel();
            contenido.setBackground(blue);
            // inicio del panel
            this.initClass.setLayout(grid);
            grBag.gridx = 0;
            grBag.gridy = 0;
            grBag.weightx = 0.15;
            grBag.weighty = 1.0;
            grBag.fill = GridBagConstraints.BOTH;
            layoutBarra(barra);
            this.initClass.add(barra, grBag);
            grBag.gridx = 1;
            grBag.gridy = 0;
            grBag.weightx = 0.7;
            grBag.weighty = 1.0;
            grBag.fill = GridBagConstraints.BOTH;
            layoutContenido(contenido);
            this.initClass.add(contenido, grBag);
        }

        private void layoutBarra(final JPanel componente) {
            JPanel seccionRama = new JPanel();
            JPanel stashes = new JPanel();
            JPanel remotos = new JPanel();
            this.paneles.add(EstructuraPanel.builder().etiqueta("seccionRama").actualComponente(seccionRama).build());
            this.paneles.add(EstructuraPanel.builder().etiqueta("stashes").actualComponente(stashes).build());
            this.paneles.add(EstructuraPanel.builder().etiqueta("remotos").actualComponente(remotos).build());
            GridBagLayout grbly = new GridBagLayout();
            GridBagConstraints grbcs = new GridBagConstraints();
            componente.setLayout(grbly);
            for (int i = 0; i < this.paneles.size(); i++) {
                grbcs.gridx = 0;
                grbcs.gridy = i;
                grbcs.weighty = 0;
                grbcs.weightx = 1;
                grbcs.fill = GridBagConstraints.BOTH;
                grbcs.insets = new Insets(5, 20, 5, 20);
                componente.add(this.paneles.get(i).getActualComponente(), grbcs);
                AreaComponente(this.paneles.get(i).getActualComponente(), new FlowLayout(FlowLayout.CENTER), new JTextField("buscar..."), this.paneles.get(i).getEtiqueta());
            }
        }

        private void layoutContenido(final JPanel componente) {

        }

        private void AreaComponente(final JPanel panelArea, LayoutManager managerLayout, JTextField buscador, String areaEtiqueta) {
            try {
                JLabel areaIcon = null;
                panelArea.setLayout(managerLayout);
                SVGIcon icon_collapse_button = new SVGIcon();
                icon_collapse_button.setSvgURI(BuildLayoutGlass.class
                        .getClassLoader()
                        .getResource("static/arrow-down-svgrepo-com.svg")
                        .toURI());
                icon_collapse_button.setPreferredSize(new Dimension(20, 20));
                icon_collapse_button.setScaleToFit(true);
                JButton btn_collapse_area = new JButton();
                btn_collapse_area.setIcon(icon_collapse_button);
                btn_collapse_area.setPreferredSize(new Dimension(30, 40));
                //personalizacion del buscador
                buscador.setPreferredSize(new Dimension(70, 20));
                panelArea.add(btn_collapse_area);
                panelArea.add(buscador);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getLocalizedMessage());
            }
        }
    }
}
