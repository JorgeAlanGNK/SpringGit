package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;

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

    public PreparedStatement SendProperties(Connection conn, String query, Object... props) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(query);
        if (!Objects.isNull(props) && props.length > 0) {
            for(int i = 0; i < props.length; i++){
                ps.setObject(i, props[i]);
            }
        }
        return ps;
    }

    public List<String> FormatoColumnas(String query) {
        return Lists.newArrayList(
                query.substring(query.indexOf("SELECT"), query.lastIndexOf("FROM"))
                        .replace("SELECT", "")
                        .trim()
                        .split(","));
    }

    public void FormatError(Throwable ex, String query) {
        System.out.println("Error query:" + query);
        System.out.println(ex.getMessage());
        System.out.println(ex.getLocalizedMessage());
        System.out.println("NameClassError: " + ex.getClass().getName());
        ex.printStackTrace();
    }

    private boolean EsPrimitivo(Class<?> tipo) {
        return tipo.isPrimitive()
                || tipo == String.class
                || tipo == Integer.class
                || tipo == Double.class
                || tipo == Long.class
                || tipo == Float.class
                || tipo == Character.class
                || tipo == Byte.class
                || tipo == Boolean.class;
    }

    private <T> void AssignModel(final Map<String, Object> propsQuery, final T model) throws Exception {
        Map<String, Field> fieldMap = Arrays.stream(model.getClass().getDeclaredFields())
                .collect(Collectors.toMap(Field::getName, Function.identity()));
        for (Entry<String, Object> prop : propsQuery.entrySet()) {
            Field campo = fieldMap.get(prop.getKey());
            if (campo != null) {
                campo.setAccessible(true);
                campo.set(prop.getKey(), prop.getValue());
            }
        }
    }
}
