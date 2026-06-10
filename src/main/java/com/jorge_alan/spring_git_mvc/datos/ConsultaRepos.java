package com.jorge_alan.spring_git_mvc.datos;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.modelos.datosModelos.DatosModelos.GitToken;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class ConsultaRepos implements UsuarioGitDB {

    private InputStreamReader inDatabase;

    public ConsultaRepos() {
        this.inDatabase = new InputStreamReader(ConsultaRepos.class.getClassLoader().getResourceAsStream("database\\gitUserDB.db"));
    }

    private Connection Database() throws SQLException, Exception, IOException {
        BufferedReader reader = new BufferedReader(inDatabase);
        String info = "";
        Connection conn = null;
        while ((info = reader.readLine()) != null) {
            conn = DriverManager.getConnection(info);
        }
        return conn;
    }

    //los metodos para las querys, hay que separar
    private boolean UpdateQuery(Connection database, String queryGeneral, Supplier<List<ParamValue>> referencias) throws SQLException, InterruptedException {
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

    //referencias: siempre guardarlo en una variable.
    private <T> List<T> QueryModel(Connection database, String queryGeneral, List<ParamValue> paramNames, Supplier<List<ParamValue>> referencias, Supplier<T> Initializer, BiConsumer<T, ParamValue> Props) throws SQLException, InterruptedException {
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
                new ParamValue("nombre_repo", JDBCType.VARCHAR)
        );
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
        try (Connection conn = Database(); PreparedStatement consultaToken = conn.prepareStatement(""); ResultSet tokenSet = consultaToken.executeQuery()) {

        } catch (Exception e) {
        }
        return temp;
    }

    @Override
    public Boolean RegistroRepositorio(String gitNombreLocal) {
        String query = "INSERT INTO GitRepositorios(git_nombre_local) VALUES (?)";
        Supplier<List<ParamValue>> referencias = () -> Lists.newArrayList(
                new ParamValue("git_nombre_local", gitNombreLocal, JDBCType.VARCHAR)
        );
        try (Connection database = Database()) {

        } catch (Exception e) {
        }
        return false;
    }

}
