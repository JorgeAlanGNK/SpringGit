package com.jorge_alan.spring_git_mvc.datos;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import java.io.IOException;
import java.util.List;

public interface BufferComando {

    List<RamaModelo> LecturaRama(String streamResult) throws IOException, InterruptedException;

    List<StashModelo> StashLectura(String streamResult) throws IOException, InterruptedException;

    boolean SwitchCambio(String streamResult, String branchSwitch) throws IOException, InterruptedException;
    
    List<RamaModelo> RamasRemotas (String streamResult) throws IOException, InterruptedException;
    
    RemotoModelo RemotosUrl(String streamResult) throws IOException, InterruptedException;
}
