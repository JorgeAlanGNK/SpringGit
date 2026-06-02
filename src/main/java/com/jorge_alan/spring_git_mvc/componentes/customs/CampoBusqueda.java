package com.jorge_alan.spring_git_mvc.componentes.customs;

import javax.swing.JTextField;

public class CampoBusqueda extends JTextField {
    
    private String hintText;

    public CampoBusqueda() {
    }

    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }
    
}
