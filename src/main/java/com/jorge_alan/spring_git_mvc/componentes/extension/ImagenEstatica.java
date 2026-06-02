package com.jorge_alan.spring_git_mvc.componentes.extension;

import javax.swing.Icon;

public class ImagenEstatica {

    private IconoExtension icono = new IconoExtensionImpl();

    public Icon GenerarIcono(String nombre, int ancho, int altura) {
        return this.icono.MostrarIcono(nombre, ancho, ancho);
    }

}
