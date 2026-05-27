package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface UsuarioGitDB {
    
    public List<GitToken> ObtenerTokensActivos();
    public Boolean RegistroRepositorio(String nombreLocal);
    public GitToken ObtenerTokenDatos(String token);
    
}
