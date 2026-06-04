package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.function.BiConsumer;
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

    public <T> List<T> QueryModel(Connection db, String queryGeneral, List<ParamValue> paramNames,
            Supplier<List<ParamValue>> referencias, Supplier<T> getInstance, BiConsumer<T, ParamValue> propsReferences)
            throws SQLException, InterruptedException {
        List<ParamValue> params = referencias.get();
        List<T> valorActual = Lists.newArrayList();
        try (PreparedStatement ps = db.prepareStatement(queryGeneral)) {
            if (paramNames != null && queryGeneral.contains("?")) {
                for (int i = 1; i <= paramNames.size(); i++) {
                    ps.setObject(i, paramNames.get(i - 1).getValor());
                }
            }
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    T modelo = getInstance.get();
                    for (ParamValue paramName : params) {
                        Object valorParam = rs.getObject(paramName.getParametro());
                        paramName.setValor(valorParam);
                        propsReferences.accept(modelo, paramName);//este es el set del objeto
                    }
                    valorActual.add(modelo);
                }
            }
        }
        return valorActual;
    }
}
