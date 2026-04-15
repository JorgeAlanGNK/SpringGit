package com.jorge_alan.spring_git_mvc.datos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;

import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaDatos {

    @Builder
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static final class GitVisualizacion implements IGitVisualizacion {
        
        @Override
        public CompletableFuture<List<RamaModelo>> ObtenerRamas() {
            return CompletableFuture.supplyAsync(() -> new ArrayList<>());
        }

        @Override
        public CompletableFuture<List<StashModelo>> ObtenerStashes() {
            CompletableFuture<List<StashModelo>> futureStash = CompletableFuture.supplyAsync(() -> {
                try {
                    ProcessBuilder builder = new ProcessBuilder("git", "stash", "list");
                    Process p = builder.start();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    String version = null;
                    while((!Strings.isNullOrEmpty(reader.readLine()))) {
                        version += reader.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return new ArrayList<>();
            });
            return futureStash;
        }

    }

    public interface IGitVisualizacion {
        
        CompletableFuture<List<RamaModelo>> ObtenerRamas();

        CompletableFuture<List<StashModelo>> ObtenerStashes();
        
    }
}
