package com.jorge_alan.spring_git_mvc.modelos.datosModelos;

import java.util.Date;

public class DatosModelos {

    private DatosModelos() throws InterruptedException {
        throw new InterruptedException("No se puede instanciar");
    }

    public static final class GitToken {

        private int id_token;
        private String token_ref;
        private Date fecha_caducidad;
        private String descripcion;
        private String nombre_repo;

        public int getId_token() {
            return id_token;
        }

        public void setId_token(int id_token) {
            this.id_token = id_token;
        }

        public String getToken_ref() {
            return token_ref;
        }

        public void setToken_ref(String token_ref) {
            this.token_ref = token_ref;
        }

        public Date getFecha_caducidad() {
            return fecha_caducidad;
        }

        public void setFecha_caducidad(Date fecha_caducidad) {
            this.fecha_caducidad = fecha_caducidad;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getNombre_repo() {
            return nombre_repo;
        }

        public void setNombre_repo(String nombre_repo) {
            this.nombre_repo = nombre_repo;
        }

    }

}
