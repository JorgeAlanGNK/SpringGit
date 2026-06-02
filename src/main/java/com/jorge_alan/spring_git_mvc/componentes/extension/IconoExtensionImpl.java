package com.jorge_alan.spring_git_mvc.componentes.extension;

import com.kitfox.svg.app.beans.SVGIcon;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;

public class IconoExtensionImpl implements IconoExtension {

    @Override
    public Icon MostrarIcono(String nombre, int w, int h) {
        //solo svg, para componentes directos
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

    @Override
    public Icon MostrarIconoDesktop(String nombre, int w, int h) {
        //solo png gif y jpeg
        try {
            FlatSVGIcon icon = new FlatSVGIcon(nombre, w, h, getClass().getClassLoader());
            return icon;
        } catch (Exception e) {
            System.out.println("formato no permitido");
            return null;
        }
    }

}
