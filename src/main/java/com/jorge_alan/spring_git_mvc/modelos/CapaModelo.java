package com.jorge_alan.spring_git_mvc.modelos;

import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import lombok.ToString;

public final class CapaModelo {

    // las estructuras pertenecen a paneles y operaciones de componentes
    private CapaModelo() throws InterruptedException {
        throw new InterruptedException("No se puede instanciar este objeto");
    }

    //la nomenclatura modelo funciona para realizar operaciones JPanel
    //falta realizar información detallada
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @Builder
    public static final class RamaModelo {

        private String nombreRama;
        private List<String> carpetas;
        private boolean carpeta;
        private boolean origin;

        public String getNombreRama() {
            return nombreRama;
        }

        public void setNombreRama(String nombreRama) {
            this.nombreRama = nombreRama;
        }

        public List<String> getCarpetas() {
            return carpetas;
        }

        public void setCarpetas(List<String> carpetas) {
            this.carpetas = carpetas;
        }

        public boolean isCarpeta() {
            return carpeta;
        }

        public void setCarpeta(boolean carpeta) {
            this.carpeta = carpeta;
        }

        public boolean isOrigin() {
            return origin;
        }

        public void setOrigin(boolean origin) {
            this.origin = origin;
        }

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    public static final class StashModelo {

        private String stash;
        private int indexStash;

        public String getStash() {
            return stash;
        }

        public void setStash(String stash) {
            this.stash = stash;
        }

        public int getIndexStash() {
            return indexStash;
        }

        public void setIndexStash(int indexStash) {
            this.indexStash = indexStash;
        }

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static final class RemotoModelo {

        private String fetch;
        private String push;

        public String getFetch() {
            return fetch;
        }

        public void setFetch(String fetch) {
            this.fetch = fetch;
        }

        public String getPush() {
            return push;
        }

        public void setPush(String push) {
            this.push = push;
        }

    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static final class ModalToken {

        private String repo;
        private String urlRemote;
        private String descripcion;
        private boolean activarToken;
        private Date fechaCaducidad;
        private String tokenPath;

        public String getRepo() {
            return repo;
        }

        public void setRepo(String repo) {
            this.repo = repo;
        }

        public String getUrlRemote() {
            return urlRemote;
        }

        public void setUrlRemote(String urlRemote) {
            this.urlRemote = urlRemote;
        }
    }
}
