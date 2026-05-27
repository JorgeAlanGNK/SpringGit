package com.jorge_alan.spring_git_mvc.modelos.representaciones;

public class GitRepositorio {

    private Integer id_repositorio;
    private String git_nombre_local;
    private String git_nombre_url;
    private Integer es_activo;
    private Integer id_token;

    public GitRepositorio() {
    }

    public GitRepositorio(String git_nombre_local, String git_nombre_url, Integer es_activo, Integer id_token) {
        this.git_nombre_local = git_nombre_local;
        this.git_nombre_url = git_nombre_url;
        this.es_activo = es_activo;
        this.id_token = id_token;
    }

    public Integer getId_repositorio() {
        return id_repositorio;
    }

    public void setId_repositorio(Integer id_repositorio) {
        this.id_repositorio = id_repositorio;
    }

    public String getGit_nombre_local() {
        return git_nombre_local;
    }

    public void setGit_nombre_local(String git_nombre_local) {
        this.git_nombre_local = git_nombre_local;
    }

    public String getGit_nombre_url() {
        return git_nombre_url;
    }

    public void setGit_nombre_url(String git_nombre_url) {
        this.git_nombre_url = git_nombre_url;
    }

    public Integer getEs_activo() {
        return es_activo;
    }

    public void setEs_activo(Integer es_activo) {
        this.es_activo = es_activo;
    }

    public Integer getId_token() {
        return id_token;
    }

    public void setId_token(Integer id_token) {
        this.id_token = id_token;
    }

}
