package com.jorge_alan.spring_git_mvc.datos;

import java.util.List;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;

public class DaoGitUsuario {
    
    private UsuarioGitDB info;

    public DaoGitUsuario() {
        info = new ConsultaRepos();
    }
    
    protected List<GitToken> TokenActivos() {
        return info.ObtenerTokensActivos();
    }
    
    private void GuardarToken() {
        
    }
    
    
}
