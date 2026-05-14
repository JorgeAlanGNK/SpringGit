package com.jorge_alan.spring_git_mvc.datos;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ComandoUpdate implements BufferComando {

    @Override
    public Set<RamaModelo> LecturaRama(String resultStream) throws IOException, InterruptedException {
        Objects.requireNonNull(resultStream, "No se puede leer el buffer");
        Set<RamaModelo> ramaModelo = Sets.newHashSet();
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

    @Override
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

    @Override
    public boolean SwitchCambio(String streamResult, String branchSwitch) throws IOException, InterruptedException {
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

    @Override
    public List<RamaModelo> RamasRemotas(String streamResult) throws IOException, InterruptedException {
        Objects.requireNonNull(streamResult);
        String[] rama = streamResult.split("\\R+");
        List<RamaModelo> ramaResultado = Lists.newArrayList();
        for (String lineaRama : rama) {
            lineaRama = lineaRama.trim();
            //en caso de que tenga HEAD, no contar la rama
            if (Strings.isNullOrEmpty(lineaRama) || lineaRama.contains("HEAD ->")) {
                continue;
            }
            RamaModelo objRemoto = new RamaModelo();
            String[] splitRemote = null;
            List<String> carpetas = Lists.newArrayList();
            boolean hayCarpetas = lineaRama.contains("/");
            int pivoteRama = 0;
            if (hayCarpetas) {
                splitRemote = lineaRama.split("/");
                pivoteRama = splitRemote.length - 1;//pivote para ignorar la rama
                for (int i = 0; i <= pivoteRama - 1; i++) {
                    if (splitRemote[i].equals("origin")) {
                        continue;
                    }
                    carpetas.add(splitRemote[i]);
                }
                objRemoto.setCarpetas(carpetas);
                objRemoto.setNombreRama(splitRemote[pivoteRama]);
                objRemoto.setOrigin(true);
                objRemoto.setCarpeta(!carpetas.isEmpty());
            }
            ramaResultado.add(objRemoto);
        }
        return ramaResultado;
    }

    @Override
    public RemotoModelo RemotosUrl(String streamResult) throws IOException, InterruptedException {
        Objects.requireNonNull(streamResult);
        String[] remotos = streamResult.split("\\R+");
        RemotoModelo setUrlObj = new RemotoModelo();
        for (String remoto : remotos) {
            remoto = remoto.replace("origin", "").trim();
            if(remoto.contains("(fetch)")) {
                remoto = remoto.replace("(fetch)", "").trim().replace(".git", "").trim();
                setUrlObj.setFetch(remoto);
            } else if (remoto.contains("(push)")) {
                remoto = remoto.replace("(fetch)", "").trim().replace(".git", "").trim();
                setUrlObj.setPush(remoto);
            }
        }
        return setUrlObj;
    }
}
