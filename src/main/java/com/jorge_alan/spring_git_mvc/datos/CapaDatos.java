package com.jorge_alan.spring_git_mvc.datos;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;

import java.lang.ProcessBuilder;
import java.lang.Process;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CapaDatos {

    //falta agregar el clone de git
    @Builder
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static final class GitVisualizacion implements IGitVisualizacion {

        @Override
        public CompletableFuture<List<RamaModelo>> ObtenerRamas(String repositorio) {
            CompletableFuture<List<RamaModelo>> taskInit = CompletableFuture.supplyAsync(() -> {
                List<RamaModelo> ramaModelo = Lists.newArrayList();
                BufferComando cmd = new BufferComando();
                try {
                    Path carpeta = Paths.get(repositorio);
                    if(!Files.isDirectory(carpeta)) {
                        throw new IllegalArgumentException("No se puede leer este repositorio o no existe, favor de validar");
                    }
                    String lineaStream = cmd.Comando(carpeta, "git", "branch");
                    ramaModelo.addAll(cmd.LecturaRama(lineaStream));
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
                BufferComando cmd = new BufferComando();
                try {
                    Path directorio = Paths.get(repositorio);
                    if(!Files.isDirectory(directorio)) {
                        throw new IllegalArgumentException("No se puede leer este repositorio o no existe, favor de validar");
                    }
                    String lineaRama = cmd.Comando(directorio, "git", "stash", "list");
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
                    BufferComando cmd = new BufferComando();
                    Path directorio = Paths.get(repositorio);
                    if(!Files.isDirectory(directorio)) {
                        throw new IllegalArgumentException("No se puede leer este repositorio o no existe, favor de validar");
                    }
                    String lineaRama = cmd.Comando(directorio, "git", "switch", nombreRama);
                    boolean resultado = cmd.SwitchCambio(lineaRama, nombreRama);
                    return resultado;
                } catch (Exception e) {
                    System.out.println("Error");
                    e.printStackTrace();
                    return false;
                }
            });
        }

        @Override
        public CompletableFuture<List<RamaModelo>> ObtenerRemotos(String repositorio) {
            return CompletableFuture.supplyAsync(() -> {
                List<RamaModelo> ramas = Lists.newArrayList();
                BufferComando cmd = new BufferComando();
                try {
                    Path directorio = Paths.get(repositorio);
                    if(!Files.isDirectory(directorio)) {
                        throw new IllegalArgumentException("No se puede leer este repositorio o no existe, favor de validar");
                    }
                    String remotos = cmd.Comando(directorio, "git", "branch", "-r");
                    ramas.addAll(cmd.RamasRemotas(remotos));
                } catch (Exception e) {
                    System.out.println("Error");
                    e.printStackTrace();
                }
                return ramas;
            });
        }

    }

    @NoArgsConstructor
    private static final class BufferComando {

        public List<RamaModelo> LecturaRama(String resultStream) throws Exception {
            Objects.requireNonNull(resultStream, "No se puede leer el buffer");
            List<RamaModelo> ramaModelo = Lists.newArrayList();
            String[] lineas = resultStream.split("\\R+");
            for (String branchLocal : lineas) {
                if (branchLocal.isBlank()) {
                    continue;
                }
                //quita el *
                if (branchLocal.trim().startsWith("*")) {
                    branchLocal = branchLocal.replace("*", "").trim();
                }
                RamaModelo objBranch = new RamaModelo();
                String splitBranch[] = null;
                List<String> tempSplit = Lists.newArrayList();
                int lenSplit = 0;
                boolean esCarpeta = branchLocal.contains("/");
                if (esCarpeta) {//aseguramos si es que existen carpetas
                    splitBranch = branchLocal.split("/");
                    lenSplit = splitBranch.length - 1;//pivote para distinguir las carpetas
                    for (int i = 0; i <= lenSplit - 1; i++) {
                        tempSplit.add(splitBranch[i]);
                    }
                    objBranch.setCarpetas(tempSplit);
                    objBranch.setCarpeta(esCarpeta);
                    objBranch.setNombreRama(splitBranch[lenSplit]);
                } else {
                    objBranch.setCarpetas(Lists.newArrayList());
                    objBranch.setCarpeta(false);
                    objBranch.setNombreRama(branchLocal);
                }
                ramaModelo.add(objBranch);
            }
            return ramaModelo;
        }

        public List<StashModelo> StashLectura(String streamResult) throws IOException, InterruptedException {
            Objects.requireNonNull(streamResult, "No se puede leer el buffer");
            List<StashModelo> resultadoStash = Lists.newArrayList();
            String[] lineas = streamResult.split("\\R+");
            int count = -1;
            for (String linea : lineas) {
                count = count + 1;
                resultadoStash.add(new StashModelo(linea, count));
            }
            return resultadoStash;
        }

        public Boolean SwitchCambio(String streamResult, String branchSwitch) throws IOException, InterruptedException {
            Objects.requireNonNull(streamResult, "No se puede leer el archivo");
            Objects.requireNonNull(branchSwitch, "Rama desconocido, favor de revisar");
            String[] lineas = streamResult.split("\\R+");
            boolean resultado = false;
            for (String linea : lineas) {
                if (Strings.isNullOrEmpty(linea)) {
                    continue;
                }
                linea = linea.trim();
                if (linea.startsWith("*")) {
                    linea = linea.replace("*", "").trim();
                }
                if (branchSwitch.equals(linea)) {
                    resultado = true;
                    break;
                }
            }
            return resultado;
        }

        public List<RamaModelo> RamasRemotas(String streamResult) {
            Objects.requireNonNull(streamResult);
            String[] rama = streamResult.split("\\R+");
            List<RamaModelo> ramaResultado = Lists.newArrayList();
            for (String lineaRama : rama) {
                if (Strings.isNullOrEmpty(lineaRama) || lineaRama.contains("HEAD ->")) {
                    continue;
                }
                if (lineaRama.startsWith("origin/")) {
                    lineaRama = lineaRama.replace("origin/", "").trim();
                }
                ramaResultado.add(new RamaModelo(lineaRama, Lists.newArrayList(), false, true));
            }
            return ramaResultado;
        }

        public String Comando(Path dir, String... comando) throws IOException, InterruptedException {
            Objects.requireNonNull(dir, "directorio no encontrado");
            Objects.requireNonNull(comando, "No se especifico los comandos de ejecución");
            if (!Files.exists(dir)) {
                throw new IllegalArgumentException("Directorio invalido para ejecutar el proceso");
            }
            if (comando.length == 0) {
                throw new IllegalArgumentException("Se detectaron campos no rellenados");
            }
            ProcessBuilder pb = new ProcessBuilder()
                    .command(comando)
                    .redirectErrorStream(true)
                    .directory(dir.toFile());
            Process start = pb.start();
            int result = start.waitFor();
            if (result != 0) {
                throw new IllegalArgumentException("Error en la ejecución del cmd");
            }
            StringBuilder stb = new StringBuilder();
            try (BufferedReader temp = new BufferedReader(new InputStreamReader(start.getInputStream(), StandardCharsets.UTF_8))) {
                String info = null;
                while ((info = temp.readLine()) != null) {
                    stb.append(info.trim()).append(System.lineSeparator());
                }
            }
            return stb.toString().trim();
        }
    }

    public interface IGitVisualizacion {

        CompletableFuture<List<RamaModelo>> ObtenerRamas(String repositorio);

        CompletableFuture<List<StashModelo>> ObtenerStashes(String repositorio);

        CompletableFuture<List<RamaModelo>> ObtenerRemotos(String repositorio);

        CompletableFuture<Boolean> SwitchRama(String repositorio, String nombreRama);
    }

}
