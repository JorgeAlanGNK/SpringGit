package com.jorge_alan.spring_git_mvc.datos.buffers;

import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.StashModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RamaModelo;
import com.jorge_alan.spring_git_mvc.modelos.CapaModelo.RemotoModelo;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface BufferComando {

    Set<RamaModelo> LecturaRama(String streamResult);

    List<StashModelo> StashLectura(String streamResult);

    boolean SwitchCambio(String streamResult, String branchSwitch);

    List<RamaModelo> RamasRemotas(String streamResult);

    RemotoModelo RemotosUrl(String streamResult);
}
