package com.jorge_alan.spring_git_mvc.datos;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import java.util.List;

public interface UsuarioGitDB {
    
    public List<GitToken> ObtenerTokensActivos();
    public GitToken ObtenerTokenDatos(String token);
    
}
