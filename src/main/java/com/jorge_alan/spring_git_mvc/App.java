package com.jorge_alan.spring_git_mvc;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.SwingUtilities;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.componentes.Principal.BuildLayoutGlass;

public class App extends JFrame {

    private Dimension sizeWindow = Toolkit.getDefaultToolkit().getScreenSize();
    private final boolean esVisible = true;
    private static BuildLayoutGlass buildPanel;
    private static App _app;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SingletonRun();
        });
    }

    private static App SingletonRun() {
        if (_app == null) {
            return new App();
        } else {
            return _app;
        }
    }

    public App() {
        InitComponent();
        App.buildPanel = BuildLayoutGlass.builder()
                .initClass(this)
                .paneles(Lists.newArrayList())
                .rutasFisicasGitLocal(Lists.newArrayList())
                .build();
        buildPanel.Builder();
    }

    private void InitComponent() {
        this.setVisible(this.esVisible);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(sizeWindow);
        this.setPreferredSize(sizeWindow);
        this.pack();
    }
}
