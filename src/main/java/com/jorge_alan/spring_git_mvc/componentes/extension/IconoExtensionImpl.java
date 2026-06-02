package com.jorge_alan.spring_git_mvc.componentes.extension;

import com.kitfox.svg.app.beans.SVGIcon;

import java.awt.Dimension;
import javax.swing.Icon;

public class IconoExtensionImpl implements IconoExtension {

    @Override
    public Icon MostrarIcono(String nombre, int w, int h) {
        try {
            SVGIcon icon = new SVGIcon();
            icon.setSvgURI(IconoExtensionImpl.class.getClassLoader().getResource(nombre).toURI());
            icon.setScaleToFit(true);
            icon.setPreferredSize(new Dimension(w, h));
            return icon;
        } catch (Exception e) {
            System.out.println("Error");
            System.out.println(e.getMessage());
            return null;
        }
    }

}
