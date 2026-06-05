package com.jorge_alan.spring_git_mvc.datos.sql_extension.daos;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.jorge_alan.spring_git_mvc.datos.buffers.ConfigurationFactory.DefaultConfiguration;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.operators.ConsultaRepos;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.operators.UsuarioGitDB;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import com.jorge_alan.spring_git_mvc.modelos.representaciones.GitRepositorio;

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
        CompletableFuture<Boolean> tarea = info.RegistroRepositorio(modelo);
        return tarea;
    }

    @Override
    public CompletableFuture<Integer> CantidadRepositorios() {
        return info.ListarReposActivos();
    }

    @Override
    public CompletableFuture<List<GitRepositorio>> ActivarRepositorios() {
        return info.ActivarRepositorios();
    }
    
}
