package com.jorge_alan.spring_git_mvc.modelos.modules;

import com.jorge_alan.spring_git_mvc.componentes.forms.ControladorFormulario;
import com.jorge_alan.spring_git_mvc.componentes.forms.FormApp;
import com.jorge_alan.spring_git_mvc.componentes.navegacion.ConstruccionNavegador;
import com.jorge_alan.spring_git_mvc.datos.capaDatos.GitVisualizacion;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.daos.DaoGitUsuario;
import com.jorge_alan.spring_git_mvc.negocios.IniciadorUsuario;

public class ModuloInicioUsuario {

    private static ConstruccionNavegador navegador;// se encarga de verificar diseños y verificaciones de ciertos
    // componentes
    private static IniciadorUsuario manejoUsuario;// capa de negocio;
    private static ControladorFormulario controladorForm;// carga del controlador

    public ModuloInicioUsuario(FormApp app) {
        navegador = IniciarNavegador(app);
        manejoUsuario = CargaInicio();
        controladorForm = FormUsuario();
    }

    private static ConstruccionNavegador IniciarNavegador(FormApp app) {
        if (navegador == null) {
            return new ConstruccionNavegador(app);
        }
        return navegador;
    }

    private static IniciadorUsuario CargaInicio() {
        if (manejoUsuario == null) {
            return new IniciadorUsuario(
                    new GitVisualizacion(),
                    new DaoGitUsuario());
        }
        return manejoUsuario;
    }

    private static ControladorFormulario FormUsuario() {
        if (controladorForm == null) {
            controladorForm = new ControladorFormulario(manejoUsuario);
        }
        return controladorForm;
    }

    public ConstruccionNavegador getNavegador() {
        return navegador;
    }

    public IniciadorUsuario getManejoUsuario() {
        return manejoUsuario;
    }

    public ControladorFormulario getControladorForm() {
        return controladorForm;
    }

}
