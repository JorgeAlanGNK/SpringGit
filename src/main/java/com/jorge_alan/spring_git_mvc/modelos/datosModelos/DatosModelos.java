package com.jorge_alan.spring_git_mvc.modelos.datosModelos;

public class DatosModelos {

    private DatosModelos() throws InterruptedException {
        throw new InterruptedException("No se puede instanciar");
    }

    public static final class GitToken {

        private Integer id_token;
        private String git_token;
        private String fecha_caducidad;
        private String descripcion;
        private String url_repo;
        private Integer seleccionar_token;
        private String organizacion;

        public Integer getId_token() {
            return id_token;
        }

        public void setId_token(Integer id_token) {
            this.id_token = id_token;
        }

        public String getGit_token() {
            return git_token;
        }

        public void setGit_token(String git_token) {
            this.git_token = git_token;
        }

        public String getFecha_caducidad() {
            return fecha_caducidad;
        }

        public void setFecha_caducidad(String fecha_caducidad) {
            this.fecha_caducidad = fecha_caducidad;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public void setDescripcion(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getUrl_repo() {
            return url_repo;
        }

        public void setUrl_repo(String url_repo) {
            this.url_repo = url_repo;
        }

        public Integer getSeleccionar_token() {
            return seleccionar_token;
        }

        public void setSeleccionar_token(Integer seleccionar_token) {
            this.seleccionar_token = seleccionar_token;
        }

        public String getOrganizacion() {
            return organizacion;
        }

        public void setOrganizacion(String organizacion) {
            this.organizacion = organizacion;
        }

    }

}
