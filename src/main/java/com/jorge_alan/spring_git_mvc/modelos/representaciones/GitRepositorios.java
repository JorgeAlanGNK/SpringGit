package com.jorge_alan.spring_git_mvc.modelos.representaciones;

import java.util.Date;

public class GitRepositorios {
    
    private String repo;
    private String urlRemote;
    private String descripcion;
    private boolean activarToken;
    private Date fechaCaducidad;
    private String tokenPath;
    public String getRepo() {
        return repo;
    }
    public void setRepo(String repo) {
        this.repo = repo;
    }
    public String getUrlRemote() {
        return urlRemote;
    }
    public void setUrlRemote(String urlRemote) {
        this.urlRemote = urlRemote;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public boolean isActivarToken() {
        return activarToken;
    }
    public void setActivarToken(boolean activarToken) {
        this.activarToken = activarToken;
    }
    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }
    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }
    public String getTokenPath() {
        return tokenPath;
    }
    public void setTokenPath(String tokenPath) {
        this.tokenPath = tokenPath;
    }
    
}
