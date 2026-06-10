package com.jorge_alan.spring_git_mvc.datos.capaDatos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.datos.buffers.BufferComando;
import com.jorge_alan.spring_git_mvc.datos.buffers.ComandoUpdate;
import com.jorge_alan.spring_git_mvc.datos.vistaEjecucion.TipoComando;
import com.jorge_alan.spring_git_mvc.datos.vistaEjecucion.WildCard;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;

public class GitVisualizacion implements IGitVisualizacion {

    private BufferComando bufferComando;

    public GitVisualizacion() {
        this.bufferComando = new ComandoUpdate();
    }

    private String ProcComando(Path dir, TipoComando areaComando, WildCard tipoCard)
            throws IOException, InterruptedException {
        Objects.requireNonNull(dir, "directorio no encontrado");
        Objects.requireNonNull(areaComando, "No se especifico los comandos de ejecución");
        if (!Files.exists(dir)) {
            throw new IllegalArgumentException("Directorio invalido para ejecutar el proceso");
        }

        // comprobar y separar los separadores
        List<String> comandoPrincipal = null;
        String[] multiSeparator = areaComando.SplitArray();
        if (multiSeparator == null) {
            comandoPrincipal = Lists.newArrayList(TipoComando.GIT.getValor(), areaComando.getValor());
        } else {
            comandoPrincipal = Lists.newArrayList(TipoComando.GIT.getValor());
            for (String separator : multiSeparator) {
                comandoPrincipal.add(separator);
            }
        }
        // se evalua la card
        if (tipoCard != null) {
            // evaluar si tiene formatos de Java %s;
            boolean cardEvaluation = tipoCard.getWildCard().contains("%s");
            if (cardEvaluation) {
                String formatCard = tipoCard.getWildCard().formatted();
            }
            comandoPrincipal.add(tipoCard.getWildCard());
        }
        System.out.println(comandoPrincipal.toString());
        ProcessBuilder pb = new ProcessBuilder()
                .command(comandoPrincipal)
                .redirectErrorStream(true)
                .directory(dir.toFile());
        Process start = pb.start();
        int result = start.waitFor();
        if (result != 0) {
            throw new IllegalArgumentException("Error en la ejecución del cmd");
        }
        StringBuilder stb = new StringBuilder();
        try (BufferedReader temp = new BufferedReader(
                new InputStreamReader(start.getInputStream(), StandardCharsets.UTF_8))) {
            String info = null;
            while ((info = temp.readLine()) != null) {
                stb.append(info.trim()).append(System.lineSeparator());
            }
        }
        return stb.toString().trim();
    }

    @Override
    public CompletableFuture<Set<RamaModelo>> ObtenerRamas(String repositorio) {
        CompletableFuture<Set<RamaModelo>> taskInit = CompletableFuture.supplyAsync(() -> {
            Set<RamaModelo> ramaModelo = Sets.newHashSet();
            try {
                Path carpeta = Paths.get(repositorio);
                if (!Files.isDirectory(carpeta)) {
                    throw new IllegalArgumentException(
                            "No se puede leer este repositorio o no existe, favor de validar");
                }
                String lineaStream = ProcComando(carpeta, TipoComando.BRANCH, null);
                ramaModelo.addAll(this.bufferComando.LecturaRama(lineaStream));
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            return ramaModelo;
        });
        return taskInit;
    }

    @Override
    public CompletableFuture<List<StashModelo>> ObtenerStashes(String repositorio) {
        CompletableFuture<List<StashModelo>> futureStash = CompletableFuture.supplyAsync(() -> {
            List<StashModelo> resultadoStash = Lists.newArrayList();
            try {
                Path directorio = Paths.get(repositorio);
                if (!Files.isDirectory(directorio)) {
                    throw new IllegalArgumentException(
                            "No se puede leer este repositorio o no existe, favor de validar");
                }
                String lineaRama = ProcComando(directorio, TipoComando.STASH_LIST, null);
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            return resultadoStash;
        });
        return futureStash;
    }

    @Override
    public CompletableFuture<Boolean> SwitchRama(String repositorio, String nombreRama) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Path directorio = Paths.get(repositorio);
                if (!Files.isDirectory(directorio)) {
                    throw new IllegalArgumentException(
                            "No se puede leer este repositorio o no existe, favor de validar");
                }
                String lineaRama = ProcComando(directorio, TipoComando.SWITCH, null);
                boolean resultado = this.bufferComando.SwitchCambio(lineaRama, nombreRama);
                return resultado;
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
                return false;
            }
        });
    }

    @Override
    public CompletableFuture<Set<RamaModelo>> ObtenerRemotos(String repositorio) {
        return CompletableFuture.supplyAsync(() -> {
            Set<RamaModelo> ramas = Sets.newHashSet();
            try {
                Path directorio = Paths.get(repositorio);
                if (!Files.isDirectory(directorio)) {
                    throw new IllegalArgumentException(
                            "No se puede leer este repositorio o no existe, favor de validar");
                }
                String remotos = ProcComando(directorio, TipoComando.BRANCH, WildCard.BRANCH_ONLY_REMOTES);
                ramas.addAll(this.bufferComando.RamasRemotas(remotos));
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            return ramas;
        });
    }

    @Override
    public CompletableFuture<Set<RemotoModelo>> ObtenerUrl(String repositorio) {
        return CompletableFuture.supplyAsync(() -> {
            Set<RemotoModelo> remoto = Sets.newHashSet();
            RemotoModelo objRemoto = new RemotoModelo();
            try {
                Path directorio = Paths.get(repositorio);
                if (!Files.isDirectory(directorio)) {
                    throw new IllegalArgumentException(
                            "No se puede leer este repositorio o no existe, favor de validar");
                }
                String remotoResultado = ProcComando(directorio, TipoComando.REMOTE, WildCard.VERBOSE);
                objRemoto = this.bufferComando.RemotosUrl(remotoResultado);
                remoto.add(objRemoto);
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            return remoto;
        });
    }

    @Override
    public CompletableFuture<Boolean> VerificarRamaRemota(String repositorio) {
        return CompletableFuture.supplyAsync(() -> {
            RemotoModelo objRama = new RemotoModelo();
            try {
                Path directorio = Paths.get(repositorio);
                if (!Files.isDirectory(directorio)) {
                    throw new IllegalArgumentException(
                            "No se puede leer este repositorio o no existe, favor de validar");
                }
                String cmdResultado = ProcComando(directorio, TipoComando.REMOTE, WildCard.VERBOSE);
                objRama = this.bufferComando.RemotosUrl(cmdResultado);
                boolean resultado = !Strings.isNullOrEmpty(objRama.getFetch())
                        && !Strings.isNullOrEmpty(objRama.getPush());
                return resultado;
            } catch (Exception e) {
                System.out.println("Error");
                e.printStackTrace();
            }
            return false;
        });
    }
}
