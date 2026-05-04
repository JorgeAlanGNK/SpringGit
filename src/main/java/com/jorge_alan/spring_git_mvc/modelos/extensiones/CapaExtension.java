package com.jorge_alan.spring_git_mvc.modelos.extensiones;

import com.jorge_alan.spring_git_mvc.componentes.forms.PanelContenedorMenu;
import com.kitfox.svg.app.beans.SVGIcon;
import java.awt.Dimension;
import javax.swing.Icon;
import static javax.swing.Spring.width;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaExtension {

    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public final static class IconoExtensionImpl implements IconoExtension {

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

    public interface IconoExtension {

        Icon MostrarIcono(String nombre, int w, int h);
    }
}
