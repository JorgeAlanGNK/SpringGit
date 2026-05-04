package com.jorge_alan.spring_git_mvc.modelos.vistasModelos;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import java.util.List;
import java.util.Set;
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
    @Builder
    public static final class SeleccionRepositorioForm {

        //para frontend hacia el backend
        private List<String> rutasRepositorios;
        private String rutaActual;

        //para backedn hacia el frontend
        private List<RamaModelo> ramasResult;
        private List<StashModelo> stashesResult;
        private List<RamaModelo> remotosResult;
        private Set<RemotoModelo> remotosUrl;
        //para obtener tipos de estados o mensajes
        private EstadoSituacion dtoResultado;
        //accionadores para activar y desactivar ciertas configuraciones
        private boolean activarDireccionUrl;
        
        public List<String> getRutasRepositorios() {
            return rutasRepositorios;
        }

        public void setRutasRepositorios(List<String> rutasRepositorios) {
            this.rutasRepositorios = rutasRepositorios;
        }

        public Set<RemotoModelo> getRemotosUrl() {
            return remotosUrl;
        }

        public void setRemotosUrl(Set<RemotoModelo> remotosUrl) {
            this.remotosUrl = remotosUrl;
        }

        public String getRutaActual() {
            return rutaActual;
        }

        public void setRutaActual(String rutaActual) {
            this.rutaActual = rutaActual;
        }

        public List<RamaModelo> getRamasResult() {
            return ramasResult;
        }

        public void setRamasResult(List<RamaModelo> ramasResult) {
            this.ramasResult = ramasResult;
        }

        public List<StashModelo> getStashesResult() {
            return stashesResult;
        }

        public void setStashesResult(List<StashModelo> stashesResult) {
            this.stashesResult = stashesResult;
        }

        public List<RamaModelo> getRemotosResult() {
            return remotosResult;
        }

        public void setRemotosResult(List<RamaModelo> remotosResult) {
            this.remotosResult = remotosResult;
        }

        public EstadoSituacion getDtoResultado() {
            return dtoResultado;
        }

        public void setDtoResultado(EstadoSituacion dtoResultado) {
            this.dtoResultado = dtoResultado;
        }

        public boolean isActivarDireccionUrl() {
            return activarDireccionUrl;
        }

        public void setActivarDireccionUrl(boolean activarDireccionUrl) {
            this.activarDireccionUrl = activarDireccionUrl;
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static final class EstadoSituacion {

        private EstadoEnum enumResult;
        private String mensaje;

        public EstadoEnum getEnumResult() {
            return enumResult;
        }

        public void setEnumResult(EstadoEnum enumResult) {
            this.enumResult = enumResult;
        }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    }

    @ToString
    public enum EstadoEnum {
        OK(200),
        ERROR(500),
        WARNING(301),
        NOT_FOUND(404),
        NO_PERMISION(401),
        FORBIDDEN_ERROR(403);
        private int resultado;

        private EstadoEnum(int resultado) {
            this.resultado = resultado;
        }

    }
}
