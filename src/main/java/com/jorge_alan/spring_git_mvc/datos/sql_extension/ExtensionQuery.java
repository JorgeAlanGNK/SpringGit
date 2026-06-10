package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

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

    //envia los valores para la consulta de este tipo '?'
    public PreparedStatement SendPropertiesQuery(Connection conn, String query, List<String> props) throws SQLException {
        Objects.requireNonNull(conn);
        PreparedStatement ps = conn.prepareStatement(query);
        if (!Objects.isNull(props) && props.size() > 0) {
            for (int i = 1; i <= props.size(); i++) {
                ps.setObject(i, props.get(i - 1));
            }
        }
        return ps;
    }

    //obtiene las columnas

    public <T> List<T> GetProperties(ResultSet rs, Class<T> modelClass, List<String> props)
            throws Exception {
        Objects.requireNonNull(modelClass);
        if (!EsPrimitivo(modelClass)) {
            List<T> tempArr = Lists.newArrayList();
            while (rs.next()) {
                T getInstance = modelClass.getDeclaredConstructor().newInstance();
                for (int i = 0; props.size() > 0; i++) {
                    Field property = getInstance.getClass().getDeclaredField(props.get(i));
                    property.setAccessible(true);
                    Class<?> typeClass = property.getType();
                    GetPrimitive(rs, typeClass, props.get(i), property);
                }
                tempArr.add(getInstance);
            }
            return tempArr;
        }
        return Lists.newArrayList();
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

    private <T> void GetPrimitive(ResultSet rs, Class<?> primitveClass, String colName, Field sendValue)
            throws SQLException, IllegalAccessException {
        if (primitveClass == String.class) {
            String value = rs.getString(colName);
            sendValue.set(sendValue, value);
        } else if (primitveClass == Integer.class) {
            int value = rs.getInt(colName);
            sendValue.setInt(sendValue, value);
        } else if (primitveClass == Double.class) {
            double value = rs.getDouble(colName);
            sendValue.setDouble(sendValue, value);
        } else if (primitveClass == Long.class) {
            long value = rs.getLong(colName);
            sendValue.setLong(sendValue, value);
        } else if (primitveClass == Float.class) {
            float value = rs.getFloat(colName);
            sendValue.setFloat(sendValue, value);
        } else if (primitveClass == Character.class) {
            char value = rs.getString(colName).charAt(0);
            sendValue.setChar(sendValue, value);
        } else if (primitveClass == Byte.class) {
            byte value = rs.getByte(colName);
            sendValue.setByte(sendValue, value);
        } else if (primitveClass == Boolean.class) {
            boolean value = rs.getBoolean(colName);
            sendValue.setBoolean(sendValue, value);
        }
    }
}
