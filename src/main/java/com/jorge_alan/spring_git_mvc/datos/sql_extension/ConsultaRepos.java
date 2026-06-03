package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import com.jorge_alan.spring_git_mvc.modelos.representaciones.GitRepositorio;

public class ConsultaRepos implements UsuarioGitDB {

    public ConsultaRepos() {
    }

    private Connection Database() throws SQLException, Exception, IOException {
        try (InputStream input = getClass().getResourceAsStream("database\\gitUserDB.db")) {
            if (input == null)
                throw new SQLException("No exsite una base de datos temporal para este archivo");
            Path tempFile = Files.createTempFile("gitUserDB", ".db");
            Files.copy(input, tempFile, StandardCopyOption.REPLACE_EXISTING);
            return DriverManager.getConnection("jdbc:sqlite:" + tempFile.toAbsolutePath());
        }
    }

    // los metodos para las querys, hay que separar
    private boolean UpdateQuery(Connection database, String queryGeneral, Supplier<List<ParamValue>> referencias)
            throws SQLException, InterruptedException {
        List<ParamValue> params = referencias.get();
        try (PreparedStatement ps = database.prepareStatement(queryGeneral)) {
            if (params != null && queryGeneral.contains("?")) {
                for (int i = 1; i <= params.size(); i++) {
                    ps.setObject(i, params.get(i).getValor(), params.get(i).getTipo());
                }
            }
            return ps.execute();
        }
    }

    // referencias: siempre guardarlo en una variable.
    private <T> List<T> QueryModel(Connection database, String queryGeneral, List<ParamValue> paramNames,
            Supplier<List<ParamValue>> referencias, Supplier<T> Initializer, BiConsumer<T, ParamValue> Props)
            throws SQLException, InterruptedException {
        List<ParamValue> params = referencias.get();
        List<T> valorActual = Lists.newArrayList();
        try (PreparedStatement ps = database.prepareStatement(queryGeneral)) {
            if (paramNames != null && queryGeneral.contains("?")) {
                for (int i = 1; i <= paramNames.size(); i++) {
                    ps.setObject(i, paramNames.get(i).getValor(), paramNames.get(i).getTipo());
                }
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    T modelo = Initializer.get();
                    for (ParamValue paramName : params) {
                        Object valorParam = rs.getObject(paramName.getParametro());
                        paramName.setValor(valorParam);
                        Props.accept(modelo, paramName);
                    }
                    valorActual.add(modelo);
                }
            }
        }
        return valorActual;
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
        try (Connection database = Database()) {
            List<GitToken> resultado = QueryModel(database, query, null, funcReferencias, initializer, setProps);
            return resultado;
        } catch (Exception ex) {
            System.getLogger(ConsultaRepos.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        return Lists.newArrayList();
    }

    @Override
    public GitToken ObtenerTokenDatos(String token) {
        GitToken temp = new GitToken();
        try (Connection conn = Database();
                PreparedStatement consultaToken = conn.prepareStatement("");
                ResultSet tokenSet = consultaToken.executeQuery()) {

        } catch (Exception e) {
        }
        return temp;
    }

    @Override
    public CompletableFuture<Boolean> RegistroRepositorio(GitRepositorio repositorio) {
        String query = "INSERT INTO GitRepositorios\n" + //
                "(git_nombre_local, git_nombre_url, es_activo, id_token)\n" + //
                "VALUES(?, ?, ?, ?);";
        Supplier<CompletableFuture<Boolean>> execute = () -> CompletableFuture.supplyAsync(() -> {
            Supplier<List<ParamValue>> valores = () -> Lists.newArrayList(
                    new ParamValue("git_nombre_local", repositorio.getGit_nombre_local(), JDBCType.VARCHAR),
                    new ParamValue("git_nombre_url", repositorio.getGit_nombre_url(), JDBCType.VARCHAR),
                    new ParamValue("es_activo", repositorio.getEs_activo(), JDBCType.INTEGER),
                    new ParamValue("id_token", repositorio.getId_token(), JDBCType.INTEGER));
            try (Connection conn = Database()) {
                boolean executable = UpdateQuery(conn, query, valores);
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
