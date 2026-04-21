package com.jorge_alan.spring_git_mvc;

import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.SwingUtilities;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.componentes.forms.FormApp;

public class App {

    private Dimension sizeWindow = Toolkit.getDefaultToolkit().getScreenSize();
    private final boolean esVisible = true;
    private static App _app;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(App::SingletonRun);
    }

    private static App SingletonRun() {
        if (_app == null) {
            return new App();
        } else {
            return _app;
        }
    }
    
    private static FormApp ActualVentana() {
        FormApp resultado = new FormApp();
        return resultado;
    }

    public App() {
        FormApp init = ActualVentana();
    }
}
