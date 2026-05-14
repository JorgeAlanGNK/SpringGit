package com.jorge_alan.spring_git_mvc.modelos.vistasModelos;

public enum EstadoEnum {

    OK(200),
    ERROR(500),
    WARNING(301),
    NOT_FOUND(404),
    NO_PERMISION(401),
    FORBIDDEN_ERROR(403);
    private int numeroEstado;

    private EstadoEnum(int numeroEstado) {
        this.numeroEstado = numeroEstado;
    }

}
