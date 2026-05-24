package com.jorge_alan.spring_git_mvc.modelos.vistasModelos;

import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoEnum;

public class EstadoSituacion {
    
    private EstadoEnum tipoEnum;
    private String mensaje;

    public EstadoSituacion() {
        mensaje = "Repositorio desconocido";
        tipoEnum = EstadoEnum.UNKNOWN_ERROR;
    }

    public EstadoEnum getTipoEnum() {
        return tipoEnum;
    }

    public void setTipoEnum(EstadoEnum tipoEnum) {
        this.tipoEnum = tipoEnum;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
}
