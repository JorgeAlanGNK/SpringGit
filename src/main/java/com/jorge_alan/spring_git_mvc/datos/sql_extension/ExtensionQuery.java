package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Supplier;

import com.google.common.collect.Lists;
import com.jorge_alan.spring_git_mvc.datos.buffers.ConfigurationFactory;

public class ExtensionQuery {

    private ConfigurationFactory factory;

    public ExtensionQuery(ConfigurationFactory factory) {
        this.factory = factory;
    }

    public Connection Database() throws SQLException, Exception, IOException {
        String database = factory.GetKeyValue("sqlite.database");
        String router = factory.GetKeyValue("sqlite.router");
        Connection conn = DriverManager.getConnection(router + database);
        return conn;
    }

    public boolean UpdateQuery(Connection database, String queryGeneral, Supplier<List<ParamValue>> referencias)
            throws SQLException, InterruptedException {
        List<ParamValue> params = referencias.get();
        try (PreparedStatement ps = database.prepareStatement(queryGeneral)) {
            if (params != null && queryGeneral.contains("?")) {
                for (int i = 1; i <= params.size(); i++) {
                    ps.setObject(i, params.get(i - 1).getValor());
                }
            }
            return ps.executeUpdate() > 0;
        }
    }

    public <T> List<T> QueryModel(Connection db, String query, List<ParamValue> columnsName,
            Supplier<List<ParamValue>> references, Supplier<T> getInstance, BiConsumer<T, ParamValue> setProps)
            throws SQLException, InterruptedException {
        List<T> resultList = Lists.newArrayList();
        try (PreparedStatement ps = db.prepareStatement(query)) {
            if (query.contains("?")) {
                List<ParamValue> getReferences = references.get();
                // se encarga de enviar y acaparar los valores hacia la consulta
                for (int i = 1; i <= getReferences.size(); i++) {
                    ps.setObject(i, getReferences.get(i - 1).getValor());
                }
            }
            try (ResultSet rs = ps.executeQuery()) {
                // se ejecuta la consulta y hay que obtener los valores
                while (rs.next()) {
                    // se lee por fila, hay que tomar los valores de la columna de cada fila
                    T instanceModel = getInstance.get();
                    for (ParamValue nameColumn : columnsName) {
                        Object resultColumn = rs.getObject(nameColumn.getParametro());
                        nameColumn.setValor(resultColumn);
                        setProps.accept(instanceModel, nameColumn);
                    }
                    // se envia el modelo completo con los valores de cada fila
                    resultList.add(instanceModel);
                }
            }
            return resultList;
        }
    }

    public void FormatError(Throwable ex, String query) {
        System.out.println("Error query:" + query);
        System.out.println(ex.getMessage());
        System.out.println(ex.getLocalizedMessage());
        System.out.println("NameClassError: " + ex.getClass().getName());
        ex.printStackTrace();
    }
}
