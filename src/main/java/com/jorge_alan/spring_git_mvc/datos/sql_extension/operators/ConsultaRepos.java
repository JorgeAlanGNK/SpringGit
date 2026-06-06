package com.jorge_alan.spring_git_mvc.datos.sql_extension.operators;

import java.sql.Connection;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.datos.buffers.ConfigurationFactory;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.ExtensionQuery;
import com.jorge_alan.spring_git_mvc.datos.sql_extension.ParamValue;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import com.jorge_alan.spring_git_mvc.modelos.representaciones.GitRepositorio;

public class ConsultaRepos implements UsuarioGitDB {

    private ExtensionQuery connectionBuilder;

    public ConsultaRepos(ConfigurationFactory factory) {
        this.connectionBuilder = new ExtensionQuery(factory);
    }

    @Override
    public List<GitToken> ObtenerTokensActivos() {
        String query = "SELECT id_token, token_ref, fecha_caducidad, descripcion, nombre_repo FROM GitToken WHERE seleccionar_token = 1";
        Supplier<List<ParamValue>> funcReferencias = () -> Lists.newArrayList(
                new ParamValue("id_token", JDBCType.INTEGER),
                new ParamValue("token_ref", JDBCType.VARCHAR),
                new ParamValue("fecha_caducidad", JDBCType.DATE),
                new ParamValue("descripcion", JDBCType.VARCHAR),
                new ParamValue("nombre_repo", JDBCType.VARCHAR));
        Supplier<GitToken> initializer = GitToken::new;
        BiConsumer<GitToken, ParamValue> setProps = (model, prop) -> {
            switch (prop.getParametro()) {
                case "id_token" ->
                    model.setId_token((int) prop.getValor());
                case "token_ref" ->
                    model.setToken_ref((String) prop.getValor());
                case "fecha_caducidad" ->
                    model.setFecha_caducidad((Date) prop.getValor());
                case "descripcion" ->
                    model.setDescripcion((String) prop.getValor());
                case "nombre_repo" ->
                    model.setNombre_repo((String) prop.getValor());
            }
        };
        try (Connection database = connectionBuilder.Database()) {
            List<GitToken> resultado = connectionBuilder.QueryModel(database, query, null, funcReferencias, initializer, setProps);
            return resultado;
        } catch (Exception ex) {
            connectionBuilder.FormatError(ex, query);
        }
        return Lists.newArrayList();
    }

    @Override
    public GitToken ObtenerTokenDatos(String token) {
        GitToken temp = new GitToken();
        try (Connection conn = connectionBuilder.Database();
                PreparedStatement consultaToken = conn.prepareStatement("");
                ResultSet tokenSet = consultaToken.executeQuery()) {

        } catch (Exception e) {
        }
        return temp;
    }

    @Override
    public CompletableFuture<Boolean> RegistroRepositorio(GitRepositorio repositorio) {
        String query = "INSERT INTO GitRepositorios" +
                "(git_nombre_local, git_nombre_url, es_activo, id_token, sesion_activa)" +
                "VALUES(?, ?, ?, ?, 1);";
        Supplier<CompletableFuture<Boolean>> execute = () -> CompletableFuture.supplyAsync(() -> {
            Supplier<List<ParamValue>> valores = () -> Lists.newArrayList(
                    new ParamValue("git_nombre_local", repositorio.getGit_nombre_local(), JDBCType.VARCHAR),
                    new ParamValue("git_nombre_url", repositorio.getGit_nombre_url(), JDBCType.VARCHAR),
                    new ParamValue("es_activo", repositorio.getEs_activo(), JDBCType.INTEGER),
                    new ParamValue("id_token", repositorio.getId_token(), JDBCType.INTEGER));
            try (Connection conn = connectionBuilder.Database()) {
                boolean executable = connectionBuilder.UpdateQuery(conn, query, valores);
                return executable;
            } catch (Exception ex) {
                connectionBuilder.FormatError(ex, query);
            }
            return false;
        });
        return execute.get();
    }

    @Override
    public CompletableFuture<Integer> ListarReposActivos() {
        Supplier<Integer> taskAsync = () -> {
            String consultas = "SELECT COUNT(id_repositorio) AS cantidad_repositorio FROM GitRepositorios WHERE sesion_activa = 1";
            try (Connection conn = connectionBuilder.Database()) {
                Supplier<Integer> modeloRepo = () -> Integer.valueOf(0);
                // aqui se necesita cargar los parametros de las columnas de la consulta
                // por ejemplo el select con el nombre de sus referencias
                List<ParamValue> consultaColumna = Lists.newArrayList(
                        new ParamValue("cantidad_repositorio"));
                BiConsumer<Integer, ParamValue> setPropsFunc = (model, value) -> {
                    switch (value.getParametro()) {
                        case "cantidad_repositorio" -> model = (Integer) value.getValor();
                    }
                };
                int result = connectionBuilder.QueryModel(conn, consultas, consultaColumna, null, modeloRepo, setPropsFunc).get(0);
                return result;
            } catch (Exception e) {
                connectionBuilder.FormatError(e, consultas);
                return 0;
            }
        };
        return CompletableFuture.supplyAsync(taskAsync);
    }

    @Override
    public CompletableFuture<List<GitRepositorio>> ActivarRepositorios() {
        Supplier<List<GitRepositorio>> taskAsync = () -> {
            List<GitRepositorio> resultQuery = null;
            String consultas = "SELECT id_repositorio, git_nombre_local, sesion_activa FROM GitRepositorios WHERE sesion_activa = 1";
            try (Connection conn = connectionBuilder.Database()) {
                Supplier<GitRepositorio> modeloRepo = GitRepositorio::new;
                // aqui se necesita cargar los parametros de las columnas de la consulta
                // por ejemplo el select con el nombre de sus referencias
                List<ParamValue> consultaColumna = Lists.newArrayList(
                        new ParamValue("id_repositorio"),
                        new ParamValue("git_nombre_local"),
                        new ParamValue("sesion_activa"));
                BiConsumer<GitRepositorio, ParamValue> setPropsFunc = (model, value) -> {
                    switch (value.getParametro()) {
                        case "id_repositorio" -> model.setId_repositorio((Integer) value.getValor());
                        case "git_nombre_local" -> model.setGit_nombre_local(String.valueOf(value.getValor()));
                        case "sesion_activa" -> model.setEs_activo((Integer) value.getValor());
                    }
                };
                resultQuery = connectionBuilder.QueryModel(conn, consultas, consultaColumna, null, modeloRepo, setPropsFunc);
                return resultQuery;
            } catch (Exception e) {
                connectionBuilder.FormatError(e, consultas);
                return Lists.newArrayList();
            }
        };
        return CompletableFuture.supplyAsync(taskAsync);
    }
    
    public CompletableFuture<GitRepositorio> ObtenerRuta(String rutaLocal) {
        return CompletableFuture.supplyAsync(() -> {
            String query = "SELECT git_nombre_local, sesion_activa FROM GitRepositorios WHERE git_nombre_local = ?";
            GitRepositorio resultado = null;
            try (Connection conn = connectionBuilder.Database()) {
                Supplier<GitRepositorio> instance = GitRepositorio::new;
                List<ParamValue> columns = Lists.newArrayList(
                    new ParamValue("git_nombre_local"),
                    new ParamValue("sesion_activa")
                );
                Supplier<List<ParamValue>> refs = () -> Lists.newArrayList(
                    new ParamValue(rutaLocal)
                );
                BiConsumer<GitRepositorio, ParamValue> setProps = (model, param) -> {
                    switch (param.getParametro()) {
                        case "git_nombre_local" -> model.setGit_nombre_local(String.valueOf(param.getValor()));
                        case "sesion_activa" -> model.setEs_activo((Integer) param.getValor());
                    }
                };
                List<GitRepositorio> repo = connectionBuilder.QueryModel(conn, query, columns, refs, instance, setProps);
                if (!repo.isEmpty()) {
                    resultado = repo.get(0);
                }
            } catch (Exception e) {
                connectionBuilder.FormatError(e, query);
            }
            return resultado;
        });
    }
}
