package com.jorge_alan.spring_git_mvc.negocios;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.datos.capaDatos.IGitVisualizacion;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.IDaoGitUsuario;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoSituacion;
import com.jorge_alan.spring_git_mvc.negocios.bridge.ActualizadorMenu;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoEnum;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class IniciadorUsuario extends ActualizadorMenu {

    public IniciadorUsuario(IGitVisualizacion comandos, IDaoGitUsuario daoGitUsuario) {
        super(comandos, daoGitUsuario);
    }

    @Override
    public void EnviarRepositorio(String repositorio) {
        super.setRepositorio(repositorio);
    }

    public CompletableFuture<ModeloRepositorio> ObtenerTareaPrincipal(boolean hayRemoto) {
        if (Strings.isNullOrEmpty(super.getRepositorio())) {
            return CompletableFuture.supplyAsync(() -> {
                EstadoSituacion dtoResultado = new EstadoSituacion();
                ModeloRepositorio repositorio = new ModeloRepositorio(dtoResultado);
                repositorio.setRepositorioActual(super.getRepositorio());
                repositorio.setActivo(false);
                repositorio.setRamasLocales(Sets.newHashSet());
                repositorio.setRamasRemotas(Sets.newHashSet());
                repositorio.setStashes(Lists.newArrayList());
                repositorio.setRepositorios(Sets.newHashSet());
                dtoResultado.setMensaje("Repositorio no valido para la operacion");
                dtoResultado.setTipoEnum(EstadoEnum.NOT_FOUND);
                if (!Strings.isNullOrEmpty(super.getRepositorio())) {
                    repositorio.getRepositorios().add(super.getRepositorio());
                }
                return repositorio;
            });
        }
        CompletableFuture<ModeloRepositorio> ResultadoTareas = null;
        if (hayRemoto) {
            ResultadoTareas = CompletarConRemoto();
        } else {
            ResultadoTareas = CompletarSinRemoto();
        }
        return ResultadoTareas.exceptionally((error) -> {
            EstadoSituacion objDto = new EstadoSituacion();
            ModeloRepositorio resultado = new ModeloRepositorio(objDto);
            resultado.setRepositorioActual(super.getRepositorio());
            resultado.setRepositorios(Sets.newHashSet());
            resultado.setActivo(false);
            resultado.setRamasLocales(Sets.newHashSet());
            resultado.setRamasRemotas(Sets.newHashSet());
            resultado.setRemotosUrl(Sets.newHashSet());
            resultado.setStashes(Lists.newArrayList());
            objDto.setTipoEnum(EstadoEnum.ERROR);
            objDto.setMensaje("Este repositorio es invalido para realizar una operacion");
            System.out.println(objDto.getMensaje());
            return resultado;
        });
    }

    public CompletableFuture<Boolean> ExisteRemotoUrl() {
        CompletableFuture<Boolean> remotoUrl = super.getComandos().VerificarRamaRemota(super.getRepositorio());
        return remotoUrl.exceptionally((error) -> {
            System.out.println(error.getMessage());
            return false;
        });
    }
    ;

    public CompletableFuture<ModeloRepositorio> RegistroRepoLocal(String dir) {
        return null;
    }

    private CompletableFuture<ModeloRepositorio> CompletarSinRemoto() {
        CompletableFuture<Set<RamaModelo>> tareaRamaLocal = super.getComandos().ObtenerRamas(super.getRepositorio());
        //en caso este en la nube, si no no trae nada y solo crea la instancia
        CompletableFuture<Set<RamaModelo>> tareaRamaRemoto = super.getComandos().ObtenerRemotos(super.getRepositorio());
        CompletableFuture<List<StashModelo>> tareaStash = super.getComandos().ObtenerStashes(super.getRepositorio());
        return CompletableFuture.allOf(tareaRamaLocal, tareaRamaRemoto, tareaStash).thenApply((v) -> {
            EstadoSituacion objDto = new EstadoSituacion();
            ModeloRepositorio resultado = new ModeloRepositorio(objDto);
            resultado.setRepositorios(Sets.newHashSet());
            if (!Strings.isNullOrEmpty(super.getRepositorio())) {
                resultado.getRepositorios().add(super.getRepositorio());
            }
            resultado.setRepositorioActual(super.getRepositorio());
            resultado.setActivo(!tareaRamaRemoto.join().isEmpty());
            resultado.setRamasLocales(tareaRamaLocal.join());
            resultado.setRamasRemotas(tareaRamaRemoto.join());
            resultado.setStashes(tareaStash.join());
            resultado.setRemotosUrl(Sets.newHashSet());
            objDto.setTipoEnum(EstadoEnum.OK);
            objDto.setMensaje("");
            return resultado;
        });
    }

    private CompletableFuture<ModeloRepositorio> CompletarConRemoto() {
        CompletableFuture<Set<RamaModelo>> tareaRamaLocal = super.getComandos().ObtenerRamas(super.getRepositorio());
        CompletableFuture<Set<RamaModelo>> tareaRamaRemoto = super.getComandos().ObtenerRemotos(super.getRepositorio());
        CompletableFuture<List<StashModelo>> tareaStash = super.getComandos().ObtenerStashes(super.getRepositorio());
        CompletableFuture<Set<RemotoModelo>> taskRemotoUrl = super.getComandos().ObtenerUrl(super.getRepositorio());
        return CompletableFuture.allOf(tareaRamaLocal, tareaRamaRemoto, tareaStash, taskRemotoUrl).thenApply(v -> {
            EstadoSituacion objDto = new EstadoSituacion();
            ModeloRepositorio resultado = new ModeloRepositorio(objDto);
            resultado.setRepositorios(Sets.newHashSet());
            resultado.setRepositorioActual(super.getRepositorio());
            resultado.setActivo(!tareaRamaRemoto.join().isEmpty());
            resultado.setRamasLocales(tareaRamaLocal.join());
            resultado.setRamasRemotas(tareaRamaRemoto.join());
            resultado.setStashes(tareaStash.join());
            resultado.setRemotosUrl(taskRemotoUrl.join());
            if (!Strings.isNullOrEmpty(super.getRepositorio())) {
                resultado.getRepositorios().add(super.getRepositorio());
            }
            objDto.setTipoEnum(EstadoEnum.OK);
            objDto.setMensaje("");
            return resultado;
        });
    }

}
