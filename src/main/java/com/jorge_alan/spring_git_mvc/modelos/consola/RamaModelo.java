package com.jorge_alan.spring_git_mvc.modelos.consola;

import java.util.List;

import lombok.ToString;

@ToString
public class RamaModelo {
    //Esta clase se utiliza para realizar la llamada en consola hacia el 

    private String nombreRama;
    private List<String> carpetas;
    private boolean carpeta;
    private boolean origin;

    public String getNombreRama() {
        return nombreRama;
    }

    public void setNombreRama(String nombreRama) {
        this.nombreRama = nombreRama;
    }

    public List<String> getCarpetas() {
        return carpetas;
    }

    public void setCarpetas(List<String> carpetas) {
        this.carpetas = carpetas;
    }

    public boolean isCarpeta() {
        return carpeta;
    }

    public void setCarpeta(boolean carpeta) {
        this.carpeta = carpeta;
    }

    public boolean isOrigin() {
        return origin;
    }

    public void setOrigin(boolean origin) {
        this.origin = origin;
    }
}
