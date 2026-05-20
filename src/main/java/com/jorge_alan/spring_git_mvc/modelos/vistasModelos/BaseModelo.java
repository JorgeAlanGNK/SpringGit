package com.jorge_alan.spring_git_mvc.modelos.vistasModelos;

import java.util.Set;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;

public class BaseModelo {
    
    private ModeloRepositorio formRepositorio;
    private EstadoSituacion situacion;

    public BaseModelo() {
        this.formRepositorio = new ModeloRepositorio();
        this.situacion = new EstadoSituacion();
    }

    public ModeloRepositorio getFormRepositorio() {
        return formRepositorio;
    }

    public void setFormRepositorio(ModeloRepositorio formRepositorio) {
        this.formRepositorio = formRepositorio;
    }

    public EstadoSituacion getSituacion() {
        return situacion;
    }

    public void setSituacion(EstadoSituacion situacion) {
        this.situacion = situacion;
    }
    
}
