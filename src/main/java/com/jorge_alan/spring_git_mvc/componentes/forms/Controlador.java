package com.jorge_alan.spring_git_mvc.componentes.forms;

import com.jorge_alan.spring_git_mvc.negocios.bridge.ActualizadorMenu;

//K se encarga de la capa de negocio
//T se encarga de trabajar con el modelo
public abstract class Controlador<K extends ActualizadorMenu, T> {

    private K menuActual;
    private T modeloPrincipal;

    public Controlador(K menuActual) {
        this.menuActual = menuActual;
    }

    //aqui van las operaciones para los modelos
    public abstract T getModelo();

    public abstract void setModelo(T modelo);

    public abstract K getDependenciaActual();

    protected T getModeloPrincipal() {
        return modeloPrincipal;
    }

    protected void setModeloPrincipal(T modeloPrincipal) {
        this.modeloPrincipal = modeloPrincipal;
    }

    protected K getControladorActual() {
        return menuActual;
    }

}
