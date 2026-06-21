package com.jorge_alan.spring_git_mvc.componentes.customs;

import java.awt.Insets;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.OptionPaneUI;

public class JOptionPaneToken extends JOptionPane {

    private JTextField inputText = new JTextField();
    private JLabel etiqueta = new JLabel();
    private EmptyBorder emptyBorder = (EmptyBorder) BorderFactory.createEmptyBorder(5, 5, 5, 5);
    private String etiquetaMensaje;

    public JOptionPaneToken() {
        setBorder(emptyBorder);
        etiqueta.setText(etiquetaMensaje);
        add(etiqueta);
        add(inputText);
    }

    public String getEtiquetaMensaje() {
        return etiquetaMensaje;
    }

    public void setEtiquetaMensaje(String etiquetaMensaje) {
        this.etiquetaMensaje = etiquetaMensaje;
    }

}
