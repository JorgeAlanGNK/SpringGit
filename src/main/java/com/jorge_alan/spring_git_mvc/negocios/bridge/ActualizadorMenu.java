package com.jorge_alan.spring_git_mvc.negocios.bridge;

import com.jorge_alan.spring_git_mvc.datos.capaDatos.IGitVisualizacion;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.daos.IDaoGitUsuario;

public abstract class ActualizadorMenu {

    //capa de datos
    private IGitVisualizacion comandos;
    private IDaoGitUsuario daoRepositorio;
    //repositorio a consultar
    private String repositorio;

    public ActualizadorMenu(IGitVisualizacion comandos, IDaoGitUsuario daoRepositorio) {
        this.comandos = comandos;
        this.daoRepositorio = daoRepositorio;
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

    public IDaoGitUsuario getDaoRepositorio() {
        return daoRepositorio;
    }
    
}
