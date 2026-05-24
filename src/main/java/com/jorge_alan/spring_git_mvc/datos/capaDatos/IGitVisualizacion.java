package com.jorge_alan.spring_git_mvc.datos.capaDatos;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;

public interface IGitVisualizacion {
    CompletableFuture<Set<RamaModelo>> ObtenerRamas(String repositorio);

    CompletableFuture<List<StashModelo>> ObtenerStashes(String repositorio);

    CompletableFuture<Set<RamaModelo>> ObtenerRemotos(String repositorio);

    CompletableFuture<Boolean> SwitchRama(String repositorio, String nombreRama);

    CompletableFuture<Set<RemotoModelo>> ObtenerUrl(String repositorio);

    CompletableFuture<Boolean> VerificarRamaRemota(String repositorio);
}
