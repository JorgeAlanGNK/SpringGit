package com.jorge_alan.spring_git_mvc.datos.sql_extension;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

    // envia los valores para la consulta de este tipo '?'
    public PreparedStatement SendPropertiesQuery(Connection conn, String query, List<ParamValue> propsValues)
            throws SQLException {
        Objects.requireNonNull(conn);
        PreparedStatement ps = conn.prepareStatement(query);
        if (!Objects.isNull(propsValues) && propsValues.size() > 0) {
            for (int i = 1; i <= propsValues.size(); i++) {
                ps.setObject(i, propsValues.get(i - 1));
            }
        }
        return ps;
    }

    public List<ParamValue> PropsValues(String query) {
        List<ParamValue> params = Lists.newArrayList();
        if (MatchWord("^SELECT", query)) {
            // obtener las columnas del comando SELECT
            Pattern selectPattern = Pattern.compile("SELECT\\s*\\(?(.+)\\)?\\s*FROM", Pattern.CASE_INSENSITIVE);
            Matcher matcher = selectPattern.matcher(query);
            if (matcher.find()) {
                String columnas = matcher.group(1);
                String[] splitCols = columnas.split(",");
                for (String col : splitCols) {
                    col = col.trim();
                    if (MatchWord("AS", query)) {
                        Pattern as_query = Pattern.compile("(.*?)\\s+[AaSs]{2}\\s+(\\w+)$");
                        Matcher as_match = as_query.matcher(col);
                        col = as_match.group(2).trim();
                    }
                    params.add(new ParamValue(col));
                }
            }
        } else if (MatchWord("^INSERT", query)) {
            // obtener las columnas del comando INSERT
            Pattern insertPattern = Pattern.compile("INSERT\\s+INTO\\s+(\\w+)\\s*\\(([^)]+)\\)",
                    Pattern.CASE_INSENSITIVE);
            Matcher matcher = insertPattern.matcher(query);
            if (matcher.find()) {
                String[] columnas = matcher.group(2).split(",");
                for (String col_value : columnas) {
                    col_value = col_value.trim();
                    params.add(new ParamValue(col_value));
                }
            }
        } else if (MatchWord("^UPDATE", query)) {
            // obtener las columnas del comando UPDATE
            String startMatch = MatchGetWord("SET", query, 0);
            String endMatch = MatchGetWord("[;]", query, 0);
        } else if (MatchWord("^DELETE", query)) {
            // obtener las columnas del comando DELETE
        }
        return params;
    }

    // obtiene las columnas

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

    // formulario para el regex
    private boolean MatchWord(String match, String value) {
        Pattern pattern = Pattern.compile(match, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }

    private String MatchGetWord(String match, String value, int n) {
        Pattern pattern = Pattern.compile(match, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.group(n);
    }
}
