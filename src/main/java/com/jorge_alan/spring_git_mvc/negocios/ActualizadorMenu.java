package com.jorge_alan.spring_git_mvc.negocios;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.SeleccionRepositorioForm;
import java.util.concurrent.CompletableFuture;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.IGitVisualizacion;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.GitVisualizacion;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.EstadoSituacion;
import com.jorge_alan.spring_git_mvc.modelos.vistasModelos.VistasModelos.EstadoEnum;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.OperacionUsuario;
import com.jorge_alan.spring_git_mvc.modelos.extensiones.CapaExtension.IconoExtension;

import java.util.List;
import java.util.Set;

public abstract class ActualizadorMenu {

    //capa de datos
    private IGitVisualizacion comandos;
    private OperacionUsuario aceptacionComando;
    //repositorio a consultar
    private String repositorio;

    public ActualizadorMenu(IGitVisualizacion comandos, OperacionUsuario aceptacionComando) {
        this.comandos = comandos;
        this.aceptacionComando = aceptacionComando;
    }

    public abstract void EnviarRepositorio(String repositorio);

    protected String getRepositorio() {
        return repositorio;
    }

    protected IGitVisualizacion getComandos() {
        return comandos;
    }

    protected void setRepositorio(String repositorio) {
        this.repositorio = repositorio;
    }

    public static class CargaUsuario extends ActualizadorMenu {

        public CargaUsuario(IGitVisualizacion comandos, OperacionUsuario aceptacionComando) {
            super(comandos, aceptacionComando);
        }

        @Override
        public void EnviarRepositorio(String repositorio) {
            super.setRepositorio(repositorio);
        }

        public CompletableFuture<SeleccionRepositorioForm> ObtenerTareaPrincipal(boolean hayRemoto) {
            if (Strings.isNullOrEmpty(super.getRepositorio())) {
                return CompletableFuture.supplyAsync(() -> {
                    SeleccionRepositorioForm repositorio = SeleccionRepositorioForm
                            .builder()
                            .ramasResult(Lists.newArrayList())
                            .remotosResult(Lists.newArrayList())
                            .stashesResult(Lists.newArrayList())
                            .dtoResultado(
                                    EstadoSituacion
                                            .builder()
                                            .enumResult(EstadoEnum.NOT_FOUND)
                                            .mensaje("Repositorio no valido para la operacion")
                                            .build()
                            )
                            .build();
                    return repositorio;
                });
            }
            CompletableFuture<SeleccionRepositorioForm> ResultadoTareas = null;
            if (hayRemoto) {
                ResultadoTareas = CompletarConRemoto();
            } else {
                ResultadoTareas = CompletarSinRemoto();
            }
            return ResultadoTareas.exceptionally((error) -> {
                SeleccionRepositorioForm resultado = SeleccionRepositorioForm
                        .builder()
                        .ramasResult(Lists.newArrayList())
                        .remotosResult(Lists.newArrayList())
                        .stashesResult(Lists.newArrayList())
                        .remotosUrl(Sets.newHashSet())
                        .dtoResultado(
                                EstadoSituacion
                                        .builder()
                                        .enumResult(EstadoEnum.ERROR)
                                        .mensaje("Este repositorio es invalido para realizar una operacion")
                                        .build()
                        )
                        .build();
                System.out.println(resultado.getDtoResultado().getMensaje());
                return resultado;
            });
        }

        private CompletableFuture<SeleccionRepositorioForm> CompletarSinRemoto() {
            CompletableFuture<List<RamaModelo>> tareaRamaLocal = super.getComandos().ObtenerRamas(super.getRepositorio());
            CompletableFuture<List<RamaModelo>> tareaRamaRemoto = super.getComandos().ObtenerRemotos(super.getRepositorio());
            CompletableFuture<List<StashModelo>> tareaStash = super.getComandos().ObtenerStashes(super.getRepositorio());
            return CompletableFuture.allOf(tareaRamaLocal, tareaRamaRemoto, tareaStash).thenApply((v) -> {
                SeleccionRepositorioForm resultado = SeleccionRepositorioForm
                        .builder()
                        .ramasResult(tareaRamaLocal.join())
                        .remotosResult(tareaRamaRemoto.join())
                        .stashesResult(tareaStash.join())
                        .remotosUrl(Sets.newHashSet())
                        .dtoResultado(
                                EstadoSituacion
                                        .builder()
                                        .enumResult(EstadoEnum.OK)
                                        .mensaje("")
                                        .build()
                        )
                        .build();
                return resultado;
            });
        }

        private CompletableFuture<SeleccionRepositorioForm> CompletarConRemoto() {
            CompletableFuture<List<RamaModelo>> tareaRamaLocal = super.getComandos().ObtenerRamas(super.getRepositorio());
            CompletableFuture<List<RamaModelo>> tareaRamaRemoto = super.getComandos().ObtenerRemotos(super.getRepositorio());
            CompletableFuture<List<StashModelo>> tareaStash = super.getComandos().ObtenerStashes(super.getRepositorio());
            CompletableFuture<Set<RemotoModelo>> taskRemotoUrl = super.getComandos().ObtenerUrl(super.getRepositorio());
            return CompletableFuture.allOf(tareaRamaLocal, tareaRamaRemoto, tareaStash, taskRemotoUrl).thenApply(v -> {
                SeleccionRepositorioForm resultado = SeleccionRepositorioForm
                        .builder()
                        .ramasResult(tareaRamaLocal.join())
                        .remotosResult(tareaRamaRemoto.join())
                        .stashesResult(tareaStash.join())
                        .remotosUrl(taskRemotoUrl.join())
                        .dtoResultado(
                                EstadoSituacion
                                        .builder()
                                        .enumResult(EstadoEnum.OK)
                                        .mensaje("")
                                        .build()
                        )
                        .build();
                return resultado;
            });
        }
    }

    public static class AplicacionUsuario extends ActualizadorMenu {

        public AplicacionUsuario(IGitVisualizacion comandos, OperacionUsuario aceptacionComando) {
            super(comandos, aceptacionComando);
        }

        @Override
        public void EnviarRepositorio(String repositorio) {
            super.setRepositorio(repositorio);
        }

        public CompletableFuture<SeleccionRepositorioForm> ObtenerTareaPrincipal() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    }
}
