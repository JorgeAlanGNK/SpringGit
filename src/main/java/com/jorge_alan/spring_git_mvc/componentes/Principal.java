package com.jorge_alan.spring_git_mvc.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jorge_alan.spring_git_mvc.componentes.Diseno.ConstruccionNavegador;
import com.jorge_alan.spring_git_mvc.componentes.events.EventosStruct.PrincipalEvento;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.GitVisualizacion;

import java.util.List;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.EstructuraPanel;
import com.jorge_alan.spring_git_mvc.negocios.CapaNegocio.BuilderNegocio;
import com.jorge_alan.spring_git_mvc.negocios.CapaNegocio.IBuilderNegocio;
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
        @Getter
        @Setter
        private List<String> rutasFisicasGitLocal = Lists.newArrayList();
        @Getter @Setter
        private String rutaActual;

        private boolean validarOperaciones;

        private static final String BUSCAR_CONST = "buscar...";
        private static final String EMPTY_STRING = Strings.nullToEmpty("");

        private static final PrincipalEvento _eventosPrincipal = IniciarEventos();
        private static final IBuilderNegocio _llamadasGit = llamadasGit();
        private static final ConstruccionNavegador _menuBar = InicioNavegador();

        private static PrincipalEvento IniciarEventos() {
            if (_eventosPrincipal == null) {
                return new PrincipalEvento();
            }
            return _eventosPrincipal;
        }

        private static IBuilderNegocio llamadasGit() {
            if (_llamadasGit == null) {
                return new BuilderNegocio(new GitVisualizacion());
            }
            return _llamadasGit;
        }

        private static ConstruccionNavegador InicioNavegador() {
            if (_menuBar == null) {
                return new ConstruccionNavegador();
            }
            return _menuBar;
        }

        @Override
        public void Builder() {
            if (!_menuBar.ComprobacionGITVersion()) {
                String mensaje = "Esta aplicación requiere forzosamente instalada la extensión GIT\\n"
                + "favor de buscar la siguiente URL https://git-scm.com/install/";
                JOptionPane.showMessageDialog(initClass, mensaje, "Instalador Git no instalado", JOptionPane.INFORMATION_MESSAGE);
                validarOperaciones = false;
            } else {
                validarOperaciones = true;
            }
            _menuBar.InicializarMenu(initClass, validarOperaciones);
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
            if (validarOperaciones) {
                this.rutasFisicasGitLocal.add(_menuBar.RutaFisica(initClass));
                this.rutaActual = this.rutasFisicasGitLocal.get(0);
            }
        }

        private void layoutBarra(final JPanel componente) {
            JPanel ramas = new JPanel();
            JPanel stashes = new JPanel();
            JPanel remotos = new JPanel();
            JPanel ramaFlow = new JPanel();
            JPanel stashFlow = new JPanel();
            JPanel remotosFlow = new JPanel();
            JPanel ramaCollapse = new JPanel();
            JPanel stashCollapse = new JPanel();
            JPanel remotosCollapse = new JPanel();
            ramaCollapse.setVisible(false);
            stashCollapse.setVisible(false);
            remotosCollapse.setVisible(false);
            this.paneles.add(new EstructuraPanel("seccionRama", ramas, false));
            this.paneles.add(new EstructuraPanel("stashes", stashes, false));
            this.paneles.add(new EstructuraPanel("remotos", remotos, false));
            // Faltaba el panel a Generar
            this.paneles.add(new EstructuraPanel("seccionRama", ramaCollapse, false));
            this.paneles.add(new EstructuraPanel("stashes", stashCollapse, false));
            this.paneles.add(new EstructuraPanel("remotos", remotosCollapse, false));
            // va atraer el doble
            GridBagLayout grbly = new GridBagLayout();
            GridBagConstraints grbcs = new GridBagConstraints();
            componente.setLayout(grbly);
            for (int i = 0; i < this.paneles.size() && i < 3; i++) {
                grbcs.gridx = 0;
                grbcs.gridy = i;
                grbcs.weighty = 0;
                grbcs.weightx = 1;
                grbcs.fill = GridBagConstraints.BOTH;
                grbcs.insets = new Insets(5, 20, 5, 20);
                componente.add(this.paneles.get(i).getActualCollapse(), grbcs);
            }
            JTextField buscador1 = new JTextField();
            buscador1.setPreferredSize(new Dimension(120, 30));
            buscador1.setText(BUSCAR_CONST);
            AreaComponente(ramas, ramaFlow, new FlowLayout(FlowLayout.CENTER),
                    buscador1, "seccionRama");
            JTextField buscador2 = new JTextField();
            buscador2.setPreferredSize(new Dimension(120, 30));
            buscador2.setText(BUSCAR_CONST);
            AreaComponente(stashes, stashFlow, new FlowLayout(FlowLayout.CENTER),
                    buscador2, "stashes");
            JTextField buscador3 = new JTextField();
            buscador3.setPreferredSize(new Dimension(120, 30));
            buscador3.setText(BUSCAR_CONST);
            AreaComponente(remotos, remotosFlow, new FlowLayout(FlowLayout.CENTER),
                    buscador3, "remotos");
        }

        private void layoutContenido(final JPanel componente) {

        }

        

        private void AreaComponente(final JPanel panelActual, final JPanel areaFlow, LayoutManager managerLayout,
                JTextField buscador,
                String areaEtiqueta) {
            try {
                BoxLayout layoutTemp = new BoxLayout(panelActual, BoxLayout.Y_AXIS);
                panelActual.setLayout(layoutTemp);
                areaFlow.setLayout(managerLayout);
                SVGIcon icon_collapse_button = new SVGIcon();
                Class<BuildLayoutGlass> recursoArea = BuildLayoutGlass.class;
                icon_collapse_button.setSvgURI(recursoArea
                        .getClassLoader()
                        .getResource("static/arrow-down-svgrepo-com.svg")
                        .toURI());
                icon_collapse_button.setPreferredSize(new Dimension(20, 20));
                icon_collapse_button.setScaleToFit(true);
                JButton btn_collapse_area = new JButton();
                btn_collapse_area.setEnabled(validarOperaciones);
                if (areaEtiqueta == "seccionRama") {
                    btn_collapse_area.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            _eventosPrincipal.CollapseBranch(e, panelActual, paneles.get(3));
                        }
                    });
                } else if (areaEtiqueta == "stashes") {
                    btn_collapse_area.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            _eventosPrincipal.CollapseStash(e, panelActual, paneles.get(4));
                        }
                    });
                } else if (areaEtiqueta == "remotos") {
                    btn_collapse_area.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            _eventosPrincipal.CollapseRemote(e, panelActual, paneles.get(5));
                        }
                    });
                }
                btn_collapse_area.setIcon(icon_collapse_button);
                btn_collapse_area.setPreferredSize(new Dimension(30, 40));
                areaFlow.add(btn_collapse_area);
                areaFlow.add(buscador);
                // aqui va el switch, para representar el área correspondiente
                panelActual.add(areaFlow);
                switch (areaEtiqueta) {
                    case "seccionRama" -> {
                        JLabel zona_etiqueta = new JLabel();
                        JLabel icon_representate = new JLabel();
                        // branch-svgrepo-com
                        SVGIcon icon_zona = new SVGIcon();
                        icon_zona.setPreferredSize(new Dimension(30, 30));
                        icon_zona.setSvgURI(recursoArea
                                .getClassLoader()
                                .getResource("static/branch-svgrepo-com.svg")
                                .toURI());
                        icon_zona.setScaleToFit(true);
                        zona_etiqueta.setPreferredSize(new Dimension(80, 50));
                        icon_representate.setPreferredSize(new Dimension(120, 50));
                        icon_representate.setIcon(icon_zona);
                        zona_etiqueta.setText("BRANCHES");
                        areaFlow.add(zona_etiqueta);
                        areaFlow.add(icon_representate);
                        panelActual.add(paneles.get(3).getActualCollapse());
                    }
                    case "stashes" -> {
                        JLabel zona_etiqueta = new JLabel();
                        JLabel icon_representate = new JLabel();
                        // git-compare-svgrepo-com
                        SVGIcon icon_zona = new SVGIcon();
                        icon_zona.setPreferredSize(new Dimension(30, 30));
                        icon_zona.setSvgURI(recursoArea
                                .getClassLoader()
                                .getResource("static/git-compare-svgrepo-com.svg")
                                .toURI());
                        icon_zona.setScaleToFit(true);
                        icon_representate.setPreferredSize(new Dimension(120, 50));
                        zona_etiqueta.setPreferredSize(new Dimension(80, 50));
                        icon_representate.setIcon(icon_zona);
                        zona_etiqueta.setText("STASHES");
                        areaFlow.add(zona_etiqueta);
                        areaFlow.add(icon_representate);
                        panelActual.add(paneles.get(4).getActualCollapse());
                    }
                    case "remotos" -> {
                        JLabel zona_etiqueta = new JLabel();
                        JLabel icon_representate = new JLabel();
                        // git-svgrepo-com
                        SVGIcon icon_zona = new SVGIcon();
                        icon_zona.setPreferredSize(new Dimension(30, 30));
                        icon_zona.setSvgURI(recursoArea
                                .getClassLoader()
                                .getResource("static/git-svgrepo-com.svg")
                                .toURI());
                        icon_zona.setScaleToFit(true);
                        zona_etiqueta.setPreferredSize(new Dimension(80, 50));
                        icon_representate.setPreferredSize(new Dimension(120, 50));
                        icon_representate.setIcon(icon_zona);
                        zona_etiqueta.setText("REMOTES");
                        areaFlow.add(zona_etiqueta);
                        areaFlow.add(icon_representate);
                        panelActual.add(paneles.get(5).getActualCollapse());
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println(e.getLocalizedMessage());
            }
        }

        
    }
}
