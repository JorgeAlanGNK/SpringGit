package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.jorge_alan.spring_git_mvc.componentes.forms.Controlador;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtension;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.SeleccionRepositorioForm;
import com.jorge_alan.spring_git_mvc.negocios.ActualizadorMenu.CargaUsuario;
import com.jorge_alan.spring_git_mvc.negocios.ActualizadorMenu;
import java.util.concurrent.CompletableFuture;

public class ControladorFormulario extends Controlador<CargaUsuario, SeleccionRepositorioForm> {

    //se implementa la capa de negocio
    public ControladorFormulario(CargaUsuario menuActual) {
        super(menuActual);
    }

    @Override
    public SeleccionRepositorioForm getModelo() {
        return super.getModeloPrincipal();
    }

    @Override
    public void setModelo(SeleccionRepositorioForm modelo) {
        super.setModeloPrincipal(modelo);
    }

    public CompletableFuture<SeleccionRepositorioForm> ProcesoInicioGit(boolean hayRemoto) {
        CargaUsuario negocio = super.getControladorActual();
        String repositorio = getModelo().getRutaActual();
        negocio.EnviarRepositorio(repositorio);
        return negocio.ObtenerTareaPrincipal(hayRemoto);
    }

}
