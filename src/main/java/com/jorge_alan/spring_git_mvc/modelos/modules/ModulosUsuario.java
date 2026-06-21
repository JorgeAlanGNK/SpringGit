package com.jorge_alan.spring_git_mvc.modelos.modules;

import com.jorge_alan.spring_git_mvc.componentes.forms.FormApp;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstruccionNavegador;
import com.jorge_alan.spring_git_mvc.datos.capaDatos.GitVisualizacion;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.daos.DaoGitUsuario;
import com.jorge_alan.spring_git_mvc.negocios.IniciadorUsuario;

public final class ModulosUsuario {
    
    private static FormApp app;
    private static IniciadorUsuario manejoUsuario = CargaInicio();// capa de negocio;
    private static ConstruccionNavegador navegador;

    public ModulosUsuario(FormApp app, ConstruccionNavegador navegador) {
        ModulosUsuario.app = app;
        ModulosUsuario.navegador = navegador;
    }

    private static IniciadorUsuario CargaInicio() {
        if (manejoUsuario == null) {
            return new IniciadorUsuario(
                    new GitVisualizacion(),
                    new DaoGitUsuario());
        }
        return manejoUsuario;
    }

    public static ConstruccionNavegador getNavegador() {
        return navegador;
    }

    public IniciadorUsuario getManejoUsuario() {
        return manejoUsuario;
    }
}
