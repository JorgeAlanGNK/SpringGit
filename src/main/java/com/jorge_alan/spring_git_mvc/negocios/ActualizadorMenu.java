package com.jorge_alan.spring_git_mvc.negocios;

import com.jorge_alan.spring_git_mvc.datos.CapaDatos.IGitVisualizacion;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.OperacionUsuario;

public abstract class ActualizadorMenu {

    //capa de datos
    private IGitVisualizacion comandos;
    private OperacionUsuario aceptacionComando;
    //repositorio a consultar
    private String repositorio;

    public ActualizadorMenu(IGitVisualizacion comandos, OperacionUsuario aceptacionComando) {
        this.comandos = comandos;
        this.aceptacionComando = aceptacionComando;
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
