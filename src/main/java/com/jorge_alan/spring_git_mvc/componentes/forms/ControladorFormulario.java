package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import com.jorge_alan.spring_git_mvc.negocios.IniciadorUsuario;
import java.util.concurrent.CompletableFuture;

public class ControladorFormulario extends Controlador<IniciadorUsuario, ModeloRepositorio> {

    //se implementa la capa de negocio
    private ControladorFormulario() {
        super(null);
        throw new UnsupportedOperationException("No se puede instanciar el controlador sin un contrato");
    }

    public ControladorFormulario(IniciadorUsuario menuActual) {
        super(menuActual);
    }

    @Override
    public ModeloRepositorio getModelo() {
        return super.getModeloPrincipal();
    }

    @Override
    public void setModelo(ModeloRepositorio modelo) {
        super.setModeloPrincipal(modelo);
    }

    public CompletableFuture<ModeloRepositorio> ProcesoInicioGit(boolean hayRemoto) {
        IniciadorUsuario negocio = super.getControladorActual();
        String repositorio = getModelo().getRepositorioActual();
        negocio.EnviarRepositorio(repositorio);
        return negocio.ObtenerTareaPrincipal(hayRemoto);
    }
    
    public CompletableFuture<Boolean> VerificarRemoto() {
        IniciadorUsuario negocio = super.getControladorActual();
        String repositorio = getModelo().getRepositorioActual();
        negocio.EnviarRepositorio(repositorio);
        return negocio.ExisteRemotoUrl();
    };
    
    //este metodo funciona para verificar varios repositorios por la cadena
    public CompletableFuture<Boolean> VerificarRemoto(String repositorio) {
        IniciadorUsuario negocio = super.getControladorActual();
        negocio.EnviarRepositorio(repositorio);//necesario para revisar
        return negocio.ExisteRemotoUrl();
    }
    
    public CompletableFuture<Boolean> IngresarRepositorio(String repositorio) {
        IniciadorUsuario negocio = getControladorActual();
        negocio.EnviarRepositorio(repositorio);
        return negocio.RegistroRepoLocal(repositorio, null, false);
    }

    public CompletableFuture<Boolean> IngresarRepositorio(String repositorio, boolean activarRemoto) {
        IniciadorUsuario negocio = getControladorActual();
        negocio.EnviarRepositorio(repositorio);
        return negocio.RegistroRepoLocal(repositorio, null, activarRemoto);
    }

}
