package com.jorge_alan.spring_git_mvc.negocios;

import com.jorge_alan.spring_git_mvc.datos.capaDatos.IGitVisualizacion;

public abstract class ActualizadorMenu {

    //capa de datos
    private IGitVisualizacion comandos;
    //repositorio a consultar
    private String repositorio;

    public ActualizadorMenu(IGitVisualizacion comandos) {
        this.comandos = comandos;
    }

    public abstract void EnviarRepositorio(String repositorio);

    protected String getRepositorio() {
        return repositorio;
    }

    protected IGitVisualizacion getComandos() {
        return comandos;
    }

    protected void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }
}
