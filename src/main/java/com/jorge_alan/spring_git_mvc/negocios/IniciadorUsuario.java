package com.jorge_alan.spring_git_mvc.negocios;

import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.datos.capaDatos.IGitVisualizacion;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.daos.DaoGitUsuario;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.daos.IDaoGitUsuario;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.ModeloRepositorio;
import com.jorge_alan.spring_git_mvc.modelos.representaciones.GitRepositorio;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoEnum;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.EstadoSituacion;
import com.jorge_alan.spring_git_mvc.negocios.bridge.ActualizadorMenu;

public class IniciadorUsuario extends ActualizadorMenu {

    public IniciadorUsuario(IGitVisualizacion comandos, IDaoGitUsuario daoGitUsuario) {
        super(comandos, daoGitUsuario);
    }

    @Override
    public void EnviarRepositorio(String repositorio) {
        super.setRepositorio(repositorio);
    }

    public CompletableFuture<ModeloRepositorio> ObtenerTareaPrincipal(boolean hayRemoto) {
        Function<Throwable, ModeloRepositorio> callErrorFunc = (error) -> {
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
        };
        Supplier<CompletableFuture<ModeloRepositorio>> ejecutarSinRemoto = () -> {
            CompletableFuture<Set<RamaModelo>> tareaRamaLocal = super.getComandos()
                    .ObtenerRamas(super.getRepositorio()).exceptionally((error) -> Sets.newHashSet());
            CompletableFuture<Set<RamaModelo>> tareaRamaRemoto = super.getComandos()
                    .ObtenerRemotos(super.getRepositorio()).exceptionally((error) -> Sets.newHashSet());
            CompletableFuture<List<StashModelo>> tareaStash = super.getComandos()
                    .ObtenerStashes(super.getRepositorio()).exceptionally((error) -> Lists.newArrayList());
            CompletableFuture<Set<RemotoModelo>> taskRemotoUrl = super.getComandos().ObtenerUrl(super.getRepositorio())
                    .exceptionally((error) -> Sets.newHashSet());
            Function<Void, ModeloRepositorio> resultadoTaskAll = (v) -> {
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
            };
            return CompletableFuture.allOf(tareaRamaLocal, tareaRamaRemoto, tareaStash, taskRemotoUrl)
                    .thenApply(resultadoTaskAll);
        };
        Supplier<CompletableFuture<ModeloRepositorio>> ejecutarConRemoto = () -> {
            CompletableFuture<Set<RamaModelo>> tareaRamaLocal = super.getComandos()
                    .ObtenerRamas(super.getRepositorio()).exceptionally((error) -> Sets.newHashSet());
            CompletableFuture<Set<RamaModelo>> tareaRamaRemoto = super.getComandos()
                    .ObtenerRemotos(super.getRepositorio()).exceptionally((error) -> Sets.newHashSet());
            CompletableFuture<List<StashModelo>> tareaStash = super.getComandos()
                    .ObtenerStashes(super.getRepositorio()).exceptionally((error) -> Lists.newArrayList());
            CompletableFuture<Set<RemotoModelo>> taskRemotoUrl = super.getComandos().ObtenerUrl(super.getRepositorio())
                    .exceptionally((error) -> Sets.newHashSet());
            Function<Void, ModeloRepositorio> taskResultAll = (v) -> {
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
            };
            return CompletableFuture.allOf(tareaRamaLocal, tareaRamaRemoto, tareaStash, taskRemotoUrl)
                    .thenApply(taskResultAll);
        };
        Supplier<CompletableFuture<ModeloRepositorio>> sinRepo = () -> {
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
        };
        if (Strings.isNullOrEmpty(super.getRepositorio())) {
            return sinRepo.get();
        }
        CompletableFuture<ModeloRepositorio> ResultadoTareas = null;
        if (hayRemoto) {
            ResultadoTareas = ejecutarConRemoto.get();
        } else {
            ResultadoTareas = ejecutarSinRemoto.get();
        }
        return ResultadoTareas.exceptionally(callErrorFunc);
    }

    public CompletableFuture<Boolean> ExisteRemotoUrl() {
        CompletableFuture<Boolean> remotoUrl = super.getComandos().VerificarRamaRemota(super.getRepositorio());
        return remotoUrl.exceptionally((error) -> {
            System.out.println(error.getMessage());
            return false;
        });
    }

    public CompletableFuture<Boolean> RegistroRepoLocal(String dir, String token, boolean hayRemoto) {
        DaoGitUsuario operacion = (DaoGitUsuario) super.getDaoRepositorio();
        Supplier<CompletableFuture<Set<RemotoModelo>>> verificarRemoto = () -> super.getComandos().ObtenerUrl(dir);
        Function<Throwable, Boolean> callErrorFunc = (error) -> {
            System.out.println(error.getMessage());
            System.out.println(error.toString());
            error.printStackTrace();
            return false;
        };
        Function<Set<RemotoModelo>, CompletableFuture<Boolean>> execute = (listaRemoto) -> {
            if (listaRemoto.isEmpty()) {
                return CompletableFuture.completedFuture(false);
            }
            List<RemotoModelo> lista = listaRemoto.stream().collect(Collectors.toList());
            return operacion.IngresarRepoLocal(dir, lista.get(0).getFetch(), 1, Integer.getInteger(token));
        };
        if (hayRemoto) {
            return verificarRemoto.get().thenCompose(execute).exceptionally(callErrorFunc);
        }
        return operacion.IngresarRepoLocal(dir, null, 0, Integer.getInteger(token)).exceptionally(callErrorFunc);
    }

    public CompletableFuture<List<ModeloRepositorio>> AperturaAplicativo() {
        IDaoGitUsuario tempConsultas = getDaoRepositorio();
        BiFunction<Integer, List<GitRepositorio>, List<GitRepositorio>> tareaCantidad = (cantidad, info) -> {
            if (cantidad > 0) {
                return info;
            }
            return Lists.newArrayList();
        };
        Function<List<GitRepositorio>, List<CompletableFuture<ModeloRepositorio>>> abrirRepos = (repositorios) -> {
            List<CompletableFuture<ModeloRepositorio>> tareasGit = Lists.newArrayList();
            if (repositorios.size() > 0) {
                for (GitRepositorio repo : repositorios) {
                    String nombreLocal = repo.getGit_nombre_local();
                    CompletableFuture<Set<RamaModelo>> tareaRamaLocal = getComandos()
                            .ObtenerRamas(nombreLocal).exceptionally((error) -> Sets.newHashSet());
                    CompletableFuture<Set<RamaModelo>> tareaRamaRemoto = getComandos()
                            .ObtenerRemotos(nombreLocal).exceptionally((error) -> Sets.newHashSet());
                    CompletableFuture<List<StashModelo>> tareaStash = getComandos()
                            .ObtenerStashes(nombreLocal).exceptionally((error) -> Lists.newArrayList());
                    CompletableFuture<Set<RemotoModelo>> tareaRemotoUrl = getComandos().ObtenerUrl(nombreLocal)
                            .exceptionally((error) -> Sets.newHashSet());
                    Function<Void, ModeloRepositorio> verificarRepositorio = (v) -> {
                        EstadoSituacion objDto = new EstadoSituacion();
                        ModeloRepositorio objDtoRepoModel = new ModeloRepositorio(objDto);
                        objDtoRepoModel.setRepositorios(Sets.newHashSet());
                        objDtoRepoModel.setRepositorioActual(nombreLocal);
                        objDtoRepoModel.setActivo(!tareaRamaRemoto.join().isEmpty());
                        objDtoRepoModel.setRamasLocales(tareaRamaLocal.join());
                        objDtoRepoModel.setRamasRemotas(tareaRamaRemoto.join());
                        objDtoRepoModel.setStashes(tareaStash.join());
                        objDtoRepoModel.setRemotosUrl(tareaRemotoUrl.join());
                        if (!Strings.isNullOrEmpty(super.getRepositorio())) {
                            objDtoRepoModel.getRepositorios().add(super.getRepositorio());
                        }
                        objDto.setTipoEnum(EstadoEnum.OK);
                        objDto.setMensaje("");
                        return objDtoRepoModel;
                    };
                    CompletableFuture<ModeloRepositorio> resultDto = CompletableFuture
                            .allOf(tareaRamaLocal, tareaRamaRemoto, tareaStash, tareaRemotoUrl)
                            .thenApply(verificarRepositorio);
                    tareasGit.add(resultDto);
                }
                return tareasGit;
            }
            return Lists.newArrayList();
        };
        Function<List<GitRepositorio>, CompletionStage<List<ModeloRepositorio>>> execute = (repositorios) -> {
            List<CompletableFuture<ModeloRepositorio>> listaFuturos = abrirRepos.apply(repositorios);
            if (listaFuturos.isEmpty()) {
                return CompletableFuture.completedFuture(Lists.newArrayList());
            }
            CompletableFuture<Void> todos = CompletableFuture.allOf(listaFuturos.toArray(new CompletableFuture[0]));
            return todos.thenApply(v -> listaFuturos.stream()
                    .map(CompletableFuture::join)
                    .collect(Collectors.toList()));
        };
        CompletableFuture<Integer> repositoriosAbiertos = tempConsultas.CantidadRepositorios();
        CompletableFuture<List<GitRepositorio>> listaRepos = tempConsultas.ActivarRepositorios();
        return repositoriosAbiertos.thenCombine(listaRepos, tareaCantidad).thenCompose(execute);
    }

}
