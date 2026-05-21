package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.google.common.base.Strings;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstanteIcono;

import com.jorge_alan.spring_git_mvc.modelos.EstructuraComponente.ImagenEstatica;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import java.awt.Dimension;
import javax.swing.JTextField;
import static com.jorge_alan.spring_git_mvc.componentes.forms.FormApp.setSituacionDto;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoEnum;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoSituacion;
import javax.swing.JOptionPane;

public class PanelContenedorMenu extends javax.swing.JPanel {

    private boolean toggleRama;
    private boolean toggleRemoto;
    private boolean toggleStash;
    private String RAMA_LOCAL_TEXTO;
    private String RAMA_STASH_TEXTO;
    private String RAMA_REMOTO_TEXTO;
    private ControladorFormulario controlador;
    private String idRepoNombre;
    private ImagenEstatica extImagenes = new ImagenEstatica();

    public PanelContenedorMenu() {
        initComponents();
    }

    public void Load(ControladorFormulario controlador) {
        this.controlador = controlador;
        this.idRepoNombre = controlador.getModelo().getRepositorioActual();
        ExecuteVisual();
    }

    //ayuda a identificar el panel con el repositorio
    public String getIdRepoNombre() {
        return idRepoNombre;
    }

    private void ResultadoGit(ModeloRepositorio response) {
        EstadoSituacion objSituacion = response.getSituacion();
        this.controlador.setModelo(response);
        if (objSituacion.getTipoEnum() == EstadoEnum.ERROR || objSituacion.getTipoEnum() == EstadoEnum.NOT_FOUND) {
            JOptionPane.showMessageDialog(this, objSituacion.getMensaje(), "Repositorio Invalido",
                    JOptionPane.WARNING_MESSAGE);
        }
        setSituacionDto(objSituacion);
        if (objSituacion.getTipoEnum() == EstadoEnum.OK) {
            this.ramaLocalArea.setRamaLocal(response.getRamasLocales(), objSituacion);
            this.ramaRemotoOrigin.setRamaRemotos(response.getRamasRemotas(), objSituacion);
        }
    }

    // ayuda para volver actualizar las ramas
    private void ExecuteVisual() {
        this.controlador.ProcesoInicioGit(false).thenAccept(this::ResultadoGit);
    }

    private void TogglePanel(javax.swing.JPanel toggleCmp, javax.swing.JPanel parent, javax.swing.JButton btnAction,
            boolean isCollapse) {
        if (isCollapse) {
            btnAction.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_BTN_UP, 20, 20));
        } else {
            btnAction.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_BTN_DOWN, 20, 20));
        }
        toggleCmp.setVisible(isCollapse);
        int ancho = parent.getWidth();
        parent.setPreferredSize(new Dimension(ancho, 600));
        parent.revalidate();
        parent.repaint();
        barra.revalidate();
        barra.repaint();
    }

    private String GuardarTexto(JTextField campo) {
        String temp = campo.getText();
        campo.setText(Strings.nullToEmpty(""));
        return temp;
    }

    private void CargarPendiente(JTextField campo, String cargarTexto) {
        campo.setText(cargarTexto);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        MenuOperaciones = new javax.swing.JPopupMenu();
        barra = new javax.swing.JPanel();
        ramasPanel = new javax.swing.JPanel();
        areaRama = new javax.swing.JPanel();
        accionRama = new javax.swing.JButton();
        campoRama = new javax.swing.JTextField();
        ramaTitulo = new javax.swing.JLabel();
        iconoRamaLocal = new javax.swing.JLabel();
        collapseRama = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ramaLocalArea = new com.jorge_alan.spring_git_mvc.componentes.customs.JTreeRamaLocal();
        remotosPanel = new javax.swing.JPanel();
        areaRemoto = new javax.swing.JPanel();
        accionRemoto = new javax.swing.JButton();
        campoRemoto = new javax.swing.JTextField();
        remotoTitulo = new javax.swing.JLabel();
        iconoRamaRemoto = new javax.swing.JLabel();
        collapseRemoto = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        ramaRemotoOrigin = new com.jorge_alan.spring_git_mvc.componentes.customs.JTreeRamaRemota();
        stashesPanel = new javax.swing.JPanel();
        areaStash = new javax.swing.JPanel();
        accionStash = new javax.swing.JButton();
        campoStash = new javax.swing.JTextField();
        stashTitulo = new javax.swing.JLabel();
        iconoRamaStash = new javax.swing.JLabel();
        collapseStash = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        stashAreaPanel = new com.jorge_alan.spring_git_mvc.componentes.customs.JTreeRamaStash();
        jPanel1 = new javax.swing.JPanel();
        contenido = new javax.swing.JPanel();
        buttonDelete2 = new com.jorge_alan.spring_git_mvc.componentes.customs.ButtonDelete();

        setLayout(new java.awt.GridBagLayout());

        barra.setBackground(new java.awt.Color(255, 0, 0));

        ramasPanel.setBackground(barra.getBackground());
        ramasPanel.setPreferredSize(null);

        areaRama.setPreferredSize(new java.awt.Dimension(277, 40));

        accionRama.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_BTN_DOWN, 20, 20));
        accionRama.addActionListener(this::accionRamaActionPerformed);

        campoRama.setText("buscar...");
        campoRama.setPreferredSize(new java.awt.Dimension(130, 27));
        campoRama.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoRamaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoRamaFocusLost(evt);
            }
        });

        ramaTitulo.setText("RAMAS");

        iconoRamaLocal.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_RAMA, 20, 20));

        javax.swing.GroupLayout areaRamaLayout = new javax.swing.GroupLayout(areaRama);
        areaRama.setLayout(areaRamaLayout);
        areaRamaLayout.setHorizontalGroup(
            areaRamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaRamaLayout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(accionRama, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoRama, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ramaTitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(iconoRamaLocal)
                .addGap(33, 33, 33))
        );
        areaRamaLayout.setVerticalGroup(
            areaRamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaRamaLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(areaRamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(accionRama, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(iconoRamaLocal)
                    .addGroup(areaRamaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoRama, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ramaTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        collapseRama.setVisible(false);
        collapseRama.setPreferredSize(new java.awt.Dimension(300, 200));
        collapseRama.setLayout(new java.awt.BorderLayout());

        jScrollPane1.setViewportView(ramaLocalArea);

        collapseRama.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout ramasPanelLayout = new javax.swing.GroupLayout(ramasPanel);
        ramasPanel.setLayout(ramasPanelLayout);
        ramasPanelLayout.setHorizontalGroup(
            ramasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaRama, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
            .addComponent(collapseRama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ramasPanelLayout.setVerticalGroup(
            ramasPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ramasPanelLayout.createSequentialGroup()
                .addComponent(areaRama, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(collapseRama, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        remotosPanel.setBackground(barra.getBackground()
        );
        remotosPanel.setPreferredSize(null);

        areaRemoto.setPreferredSize(new java.awt.Dimension(281, 40));

        accionRemoto.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_BTN_DOWN, 20, 20));
        accionRemoto.addActionListener(this::accionRemotoActionPerformed);

        campoRemoto.setText("buscar...");
        campoRemoto.setPreferredSize(new java.awt.Dimension(130, 27));
        campoRemoto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoRemotoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoRemotoFocusLost(evt);
            }
        });

        remotoTitulo.setText("REMOTOS");

        iconoRamaRemoto.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_REMOTO, 20, 20));

        javax.swing.GroupLayout areaRemotoLayout = new javax.swing.GroupLayout(areaRemoto);
        areaRemoto.setLayout(areaRemotoLayout);
        areaRemotoLayout.setHorizontalGroup(
            areaRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaRemotoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accionRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remotoTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoRamaRemoto)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        areaRemotoLayout.setVerticalGroup(
            areaRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaRemotoLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(areaRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(accionRemoto, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, areaRemotoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(remotoTitulo, javax.swing.GroupLayout.DEFAULT_SIZE, 29, Short.MAX_VALUE)
                        .addComponent(campoRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(iconoRamaRemoto, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        collapseRemoto.setVisible(false);
        collapseRemoto.setPreferredSize(new java.awt.Dimension(300, 200));
        collapseRemoto.setLayout(new java.awt.BorderLayout());

        jScrollPane2.setViewportView(ramaRemotoOrigin);

        collapseRemoto.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout remotosPanelLayout = new javax.swing.GroupLayout(remotosPanel);
        remotosPanel.setLayout(remotosPanelLayout);
        remotosPanelLayout.setHorizontalGroup(
            remotosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaRemoto, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
            .addComponent(collapseRemoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        remotosPanelLayout.setVerticalGroup(
            remotosPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(remotosPanelLayout.createSequentialGroup()
                .addComponent(areaRemoto, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(collapseRemoto, javax.swing.GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                .addContainerGap())
        );

        stashesPanel.setBackground(barra.getBackground());
        stashesPanel.setPreferredSize(null);

        areaStash.setPreferredSize(new java.awt.Dimension(284, 40));

        accionStash.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_BTN_DOWN, 20, 20));
        accionStash.addActionListener(this::accionStashActionPerformed);

        campoStash.setText("buscar...");
        campoStash.setPreferredSize(new java.awt.Dimension(130, 27));
        campoStash.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                campoStashFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                campoStashFocusLost(evt);
            }
        });

        stashTitulo.setText("STASHES");

        iconoRamaStash.setIcon(extImagenes.GenerarIcono(ConstanteIcono.ICONO_STASH, 20, 20));

        javax.swing.GroupLayout areaStashLayout = new javax.swing.GroupLayout(areaStash);
        areaStash.setLayout(areaStashLayout);
        areaStashLayout.setHorizontalGroup(
            areaStashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaStashLayout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(accionStash, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(campoStash, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stashTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(iconoRamaStash)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        areaStashLayout.setVerticalGroup(
            areaStashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaStashLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(areaStashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(areaStashLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(campoStash, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(stashTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(iconoRamaStash))
                    .addComponent(accionStash, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        collapseStash.setVisible(false);
        collapseStash.setPreferredSize(new java.awt.Dimension(300, 200));
        collapseStash.setLayout(new java.awt.BorderLayout());

        jScrollPane3.setPreferredSize(new java.awt.Dimension(60, 800));

        stashAreaPanel.setPreferredSize(new java.awt.Dimension(48, 800));
        jScrollPane3.setViewportView(stashAreaPanel);

        collapseStash.add(jScrollPane3, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout stashesPanelLayout = new javax.swing.GroupLayout(stashesPanel);
        stashesPanel.setLayout(stashesPanelLayout);
        stashesPanelLayout.setHorizontalGroup(
            stashesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaStash, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
            .addComponent(collapseStash, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        stashesPanelLayout.setVerticalGroup(
            stashesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(stashesPanelLayout.createSequentialGroup()
                .addComponent(areaStash, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(collapseStash, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 431, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout barraLayout = new javax.swing.GroupLayout(barra);
        barra.setLayout(barraLayout);
        barraLayout.setHorizontalGroup(
            barraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraLayout.createSequentialGroup()
                .addGap(0, 29, Short.MAX_VALUE)
                .addGroup(barraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(stashesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(remotosPanel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(ramasPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );
        barraLayout.setVerticalGroup(
            barraLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(barraLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ramasPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(remotosPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(stashesPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.RELATIVE;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.03;
        gridBagConstraints.weighty = 1.0;
        add(barra, gridBagConstraints);

        contenido.setBackground(new java.awt.Color(0, 0, 255));

        buttonDelete2.setForeground(new java.awt.Color(0, 0, 0));
        buttonDelete2.setText("buttonDelete2");
        buttonDelete2.setFirstColorPrimary(new java.awt.Color(255, 50, 50));
        buttonDelete2.setSecondColorPrimary(new java.awt.Color(255, 0, 0));

        javax.swing.GroupLayout contenidoLayout = new javax.swing.GroupLayout(contenido);
        contenido.setLayout(contenidoLayout);
        contenidoLayout.setHorizontalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenidoLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(buttonDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(791, Short.MAX_VALUE))
        );
        contenidoLayout.setVerticalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(contenidoLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(buttonDelete2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(966, Short.MAX_VALUE))
        );

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridheight = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.8;
        gridBagConstraints.weighty = 1.0;
        add(contenido, gridBagConstraints);
    }// </editor-fold>//GEN-END:initComponents

    private void accionRamaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_accionRamaActionPerformed
        boolean cambiador = !this.toggleRama;
        javax.swing.JPanel collapse = this.collapseRama;
        javax.swing.JPanel parent = this.ramasPanel;
        javax.swing.JButton accionador = this.accionRama;
        TogglePanel(collapse, parent, accionador, cambiador);
        this.toggleRama = cambiador;
    }// GEN-LAST:event_accionRamaActionPerformed

    private void accionRemotoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_accionRemotoActionPerformed
        boolean cambiador = !this.toggleRemoto;
        javax.swing.JPanel collapse = this.collapseRemoto;
        javax.swing.JPanel parent = this.remotosPanel;
        javax.swing.JButton accionador = this.accionRemoto;
        TogglePanel(collapse, parent, accionador, cambiador);
        this.toggleRemoto = cambiador;
    }// GEN-LAST:event_accionRemotoActionPerformed

    private void accionStashActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_accionStashActionPerformed
        boolean cambiador = !this.toggleStash;
        javax.swing.JPanel collapse = this.collapseStash;
        javax.swing.JPanel parent = this.stashesPanel;
        javax.swing.JButton accionador = this.accionStash;
        TogglePanel(collapse, parent, accionador, cambiador);
        this.toggleStash = cambiador;
    }// GEN-LAST:event_accionStashActionPerformed

    private void campoRamaFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_campoRamaFocusGained
        this.RAMA_LOCAL_TEXTO = GuardarTexto(campoRama);
    }// GEN-LAST:event_campoRamaFocusGained

    private void campoRamaFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_campoRamaFocusLost
        CargarPendiente(this.campoRama, this.RAMA_LOCAL_TEXTO);
    }// GEN-LAST:event_campoRamaFocusLost

    private void campoRemotoFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_campoRemotoFocusGained
        this.RAMA_REMOTO_TEXTO = GuardarTexto(this.campoRemoto);
    }// GEN-LAST:event_campoRemotoFocusGained

    private void campoRemotoFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_campoRemotoFocusLost
        CargarPendiente(this.campoRemoto, this.RAMA_REMOTO_TEXTO);
    }// GEN-LAST:event_campoRemotoFocusLost

    private void campoStashFocusGained(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_campoStashFocusGained
        this.RAMA_STASH_TEXTO = GuardarTexto(this.campoStash);
    }// GEN-LAST:event_campoStashFocusGained

    private void campoStashFocusLost(java.awt.event.FocusEvent evt) {// GEN-FIRST:event_campoStashFocusLost
        CargarPendiente(this.campoStash, this.RAMA_STASH_TEXTO);
    }// GEN-LAST:event_campoStashFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPopupMenu MenuOperaciones;
    private javax.swing.JButton accionRama;
    private javax.swing.JButton accionRemoto;
    private javax.swing.JButton accionStash;
    private javax.swing.JPanel areaRama;
    private javax.swing.JPanel areaRemoto;
    private javax.swing.JPanel areaStash;
    private javax.swing.JPanel barra;
    private com.jorge_alan.spring_git_mvc.componentes.customs.ButtonDelete buttonDelete2;
    private javax.swing.JTextField campoRama;
    private javax.swing.JTextField campoRemoto;
    private javax.swing.JTextField campoStash;
    private javax.swing.JPanel collapseRama;
    private javax.swing.JPanel collapseRemoto;
    private javax.swing.JPanel collapseStash;
    private javax.swing.JPanel contenido;
    private javax.swing.JLabel iconoRamaLocal;
    private javax.swing.JLabel iconoRamaRemoto;
    private javax.swing.JLabel iconoRamaStash;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private com.jorge_alan.spring_git_mvc.componentes.customs.JTreeRamaLocal ramaLocalArea;
    private com.jorge_alan.spring_git_mvc.componentes.customs.JTreeRamaRemota ramaRemotoOrigin;
    private javax.swing.JLabel ramaTitulo;
    private javax.swing.JPanel ramasPanel;
    private javax.swing.JLabel remotoTitulo;
    private javax.swing.JPanel remotosPanel;
    private com.jorge_alan.spring_git_mvc.componentes.customs.JTreeRamaStash stashAreaPanel;
    private javax.swing.JLabel stashTitulo;
    private javax.swing.JPanel stashesPanel;
    // End of variables declaration//GEN-END:variables
}
