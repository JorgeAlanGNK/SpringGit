package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;

public class DaoGitUsuario implements IDaoGitUsuario {
    
    private UsuarioGitDB info;

    public DaoGitUsuario() {
        info = new ConsultaRepos();
    }
    
    @Override
    public List<GitToken> TokenActivos() {
        return info.ObtenerTokensActivos();
    }

    @Override
    public CompletableFuture<Boolean> IngresarToken() {//aqui se ponen los modelos
        // TODO Auto-generated method stub
        return info.RegistroRepositorio(null);
    }
    
}
