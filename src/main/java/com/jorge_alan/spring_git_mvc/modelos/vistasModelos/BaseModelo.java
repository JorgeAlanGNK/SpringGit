package com.jorge_alan.spring_git_mvc.modelos.vistasModelos;

//representa todos los modelos genericos
import com.google.common.collect.Maps;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class BaseModelo {
    
    private ModeloRepositorio formRepositorio = new ModeloRepositorio();
    private EstadoSituacion situacion = new EstadoSituacion();

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
