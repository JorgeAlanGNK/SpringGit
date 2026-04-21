package com.jorge_alan.spring_git_mvc.modelos.vistasModelos;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class VistasModelos {
    
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static final class SeleccionRepositorioForm {
        //para frontend hacia el backend
        private List<String> rutasRepositorios;
        private String rutaActual;
        
        //para backedn hacia el frontend
        private List<RamaModelo> ramasResult;
        private List<StashModelo> stashesResult;
        private List<RamaModelo> remotosResult;
        //para obtener tipos de estados o mensajes
        private EstadoSituacion dtoResultado;
    }
    
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Builder
    public static final class EstadoSituacion {
        private EstadoEnum enumResult;
        private String mensaje;
    }
    
    @ToString
    public enum EstadoEnum {
        OK(200),
        ERROR(500),
        WARNING(301),
        NOT_FOUND(404);
        private int resultado;
        private EstadoEnum(int resultado) {
            this.resultado = resultado;
        }
        
    }
}
