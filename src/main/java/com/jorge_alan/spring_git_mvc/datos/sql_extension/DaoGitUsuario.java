package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import com.jorge_alan.spring_git_mvc.modelos.representaciones.GitRepositorio;
import com.jorge_alan.spring_git_mvc.datos.buffers.ConfigurationFactory.DefaultConfiguration;

public class DaoGitUsuario implements IDaoGitUsuario {
    
    private UsuarioGitDB info;
    private DefaultConfiguration config;

    public DaoGitUsuario() {
        config = DefaultConfiguration.GetInstance();
        info = new ConsultaRepos(config);
    }
    
    @Override
    public List<GitToken> TokenActivos() {
        return info.ObtenerTokensActivos();
    }

    @Override
    public CompletableFuture<Boolean> IngresarRepoLocal(String gitNombreLocal, String gitNombreRemoto, Integer esActivo,
            Integer idToken) {
        GitRepositorio modelo = new GitRepositorio(gitNombreLocal, gitNombreRemoto, esActivo, idToken);
        var tarea = info.RegistroRepositorio(modelo);
        return tarea;
    }
    
}
