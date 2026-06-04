package com.jorge_alan.spring_git_mvc.datos.sql_extension.daos;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;

public interface IDaoGitUsuario {

    List<GitToken> TokenActivos();

    CompletableFuture<Boolean> IngresarRepoLocal(String gitNombreLocal, String gitNombreRemoto , Integer esActivo, Integer idToken);

    CompletableFuture<Integer> CantidadRepositorios();
}
