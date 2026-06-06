package com.jorge_alan.spring_git_mvc.datos.sql_extension.operators;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import com.jorge_alan.spring_git_mvc.modelos.representaciones.GitRepositorio;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UsuarioGitDB {

    List<GitToken> ObtenerTokensActivos();

    CompletableFuture<Boolean> RegistroRepositorio(GitRepositorio repositorio);

    GitToken ObtenerTokenDatos(String token);

    CompletableFuture<Integer> ListarReposActivos();

    CompletableFuture<List<GitRepositorio>> ActivarRepositorios();

    CompletableFuture<GitRepositorio> ObtenerRuta(String rutaLocal);
}
