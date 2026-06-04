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

    private ConfigurationFactory factory;
    private ExtensionQuery querys;

    public ConsultaRepos(ConfigurationFactory factory) {
        this.factory = factory;
        this.querys = new ExtensionQuery(factory);
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
        try (Connection database = querys.Database()) {
            List<GitToken> resultado = querys.QueryModel(database, query, null, funcReferencias, initializer, setProps);
            return resultado;
        } catch (Exception ex) {
            System.getLogger(ConsultaRepos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return Lists.newArrayList();
    }

    @Override
    public GitToken ObtenerTokenDatos(String token) {
        GitToken temp = new GitToken();
        try (Connection conn = querys.Database();
                PreparedStatement consultaToken = conn.prepareStatement("");
                ResultSet tokenSet = consultaToken.executeQuery()) {

        } catch (Exception e) {
        }
        return temp;
    }

    @Override
    public CompletableFuture<Boolean> RegistroRepositorio(GitRepositorio repositorio) {
        String query = "INSERT INTO GitRepositorios" +
                "(git_nombre_local, git_nombre_url, es_activo, id_token)" +
                "VALUES(?, ?, ?, ?);";
        Supplier<CompletableFuture<Boolean>> execute = () -> CompletableFuture.supplyAsync(() -> {
            Supplier<List<ParamValue>> valores = () -> Lists.newArrayList(
                    new ParamValue("git_nombre_local", repositorio.getGit_nombre_local(), JDBCType.VARCHAR),
                    new ParamValue("git_nombre_url", repositorio.getGit_nombre_url(), JDBCType.VARCHAR),
                    new ParamValue("es_activo", repositorio.getEs_activo(), JDBCType.INTEGER),
                    new ParamValue("id_token", repositorio.getId_token(), JDBCType.INTEGER));
            try (Connection conn = querys.Database()) {
                boolean executable = querys.UpdateQuery(conn, query, valores);
                return executable;
            } catch (Exception ex) {
                System.out.println("Error" + query);
                System.out.println(ex.getMessage());
                System.out.println(ex.getLocalizedMessage());
                ex.printStackTrace();
            }
            return false;
        });
        return execute.get();
    }

}
