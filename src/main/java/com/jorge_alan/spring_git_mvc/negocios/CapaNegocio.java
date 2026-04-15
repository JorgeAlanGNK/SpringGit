package com.jorge_alan.spring_git_mvc.negocios;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.datos.CapaDatos.IGitVisualizacion;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaNegocio {

    public static final class BuilderNegocio implements IBuilderNegocio {

        @Getter 
        private List<RamaModelo> ramas = Lists.newArrayList();
        @Getter 
        private List<StashModelo> stashes = Lists.newArrayList();

        @Getter(value = AccessLevel.PRIVATE) @Setter(value = AccessLevel.PRIVATE)
        private IGitVisualizacion gitInyeccion;

        public BuilderNegocio(IGitVisualizacion gitInyeccion) {
            this.gitInyeccion = gitInyeccion;
        }

        @Override
        public CompletableFuture<Void> RamasRemotas() {
            return CompletableFuture.runAsync(() -> {
                List<RamaModelo> tempRamas = Lists.newArrayList();
                synchronized(this) {
                }
            });
        }

        @Override
        public CompletableFuture<Void> Stashes() {
            // TODO Auto-generated method stub
            throw new UnsupportedOperationException("Unimplemented method 'Stashes'");
        }


    }

    public interface IBuilderNegocio {
        CompletableFuture<Void> RamasRemotas();

        CompletableFuture<Void> Stashes();
    }

}
