package com.jorge_alan.spring_git_mvc.datos.sql_extension.operators;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import com.jorge_alan.spring_git_mvc.modelos.representaciones.GitRepositorio;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UsuarioGitDB {

    public List<GitToken> ObtenerTokensActivos();

    public CompletableFuture<Boolean> RegistroRepositorio(GitRepositorio repositorio);

    public GitToken ObtenerTokenDatos(String token);
}
