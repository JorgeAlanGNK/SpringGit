package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;

public interface IDaoGitUsuario {

    List<GitToken> TokenActivos();

    CompletableFuture<Boolean> IngresarToken();
}
