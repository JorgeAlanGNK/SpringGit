package com.jorge_alan.spring_git_mvc.negocios;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.IGitVisualizacion;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;

import com.jorge_alan.spring_git_mvc.modelos.CapaModeloNegocio.VisualizacionModelo;
import java.util.ArrayList;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaNegocio {


    public static final class VisualizacionRama implements IVisualizacionNegocio {

        @Getter
        private final IGitVisualizacion gitInyeccion;

        public VisualizacionRama(IGitVisualizacion gitInyeccion) {
            this.gitInyeccion = gitInyeccion;
        }

        @Override

        public CompletableFuture<VisualizacionModelo> RamasRemotas(String rutaActual) {
            CompletableFuture<List<RamaModelo>> taskBranch = this.gitInyeccion.ObtenerRamas(rutaActual);
            CompletableFuture<List<StashModelo>> taskStash = this.gitInyeccion.ObtenerStashes(rutaActual);
            CompletableFuture<List<RamaModelo>> taskRemoto = this.gitInyeccion.ObtenerRemotos(rutaActual);
            CompletableFuture<Void> combinados = CompletableFuture.allOf(taskBranch, taskStash, taskRemoto);
            CompletableFuture<VisualizacionModelo> terminacion = combinados.thenApply(v -> {
                VisualizacionModelo modelo = new VisualizacionModelo();
                List<RamaModelo> ramas = taskBranch.join();
                List<StashModelo> stashes = taskStash.join();
                List<RamaModelo> remotos = taskRemoto.join();
                modelo.setRamas(ramas);
                modelo.setStashes(stashes);
                modelo.setRemotos(remotos);
                return modelo;
            }).exceptionally((error) -> {
                System.out.println("Error");
                System.out.println(error.getMessage());
                error.printStackTrace();
                VisualizacionModelo visualizar = new VisualizacionModelo();
                visualizar.setRamas(Lists.newArrayList());
                visualizar.setStashes(Lists.newArrayList());
                visualizar.setRemotos(Lists.newArrayList());
                return visualizar;
            });
            return terminacion;
        }

    }

    public interface IVisualizacionNegocio {

        CompletableFuture<VisualizacionModelo> RamasRemotas(String rutaActual);

    }

}
