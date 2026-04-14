package com.jorge_alan.spring_git_mvc.componentes.events;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.EstructuraPanel;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventosStruct {

    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class PrincipalEvento {

        public void CollapseBranch(ActionEvent e, JPanel actual, EstructuraPanel modelPanel) {
            modelPanel.setEsVisible(!modelPanel.isEsVisible());
            JPanel collapse = modelPanel.getActualCollapse();
            if(modelPanel.isEsVisible()) {
                actual.add(collapse);
            } else {
                actual.remove(collapse);
            }
            actual.revalidate();
            actual.repaint();
        }

        public void CollapseStash(ActionEvent e, JPanel actual, EstructuraPanel modelPanel) {
            modelPanel.setEsVisible(!modelPanel.isEsVisible());
            JPanel area = modelPanel.getActualCollapse();
            area.setVisible(modelPanel.isEsVisible());
        }

        public void CollapseRemote(ActionEvent e, JPanel actual, EstructuraPanel modelPanel) {
            modelPanel.setEsVisible(!modelPanel.isEsVisible());
            if (modelPanel.isEsVisible()) {

            }
        }

    }
}
