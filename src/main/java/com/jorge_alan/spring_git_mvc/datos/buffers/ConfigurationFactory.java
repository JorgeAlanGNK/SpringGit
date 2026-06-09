package com.jorge_alan.spring_git_mvc.datos.buffers;

import java.io.InputStream;
import java.util.Properties;

public abstract class ConfigurationFactory {

    // esta clase ayuda a obtener propiedades de la configuracion
    private final Properties properties;

    public ConfigurationFactory() {
        properties = new Properties();
        LoadProperties();
    }

    protected Properties getProperties() {
        return properties;
    }

    public abstract String GetKeyValue(String key);

    public abstract boolean HasKey(String key);

    private void LoadProperties() {
        // generar las propiedades del archivo
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            getProperties().load(input);
        } catch (Exception e) {
            System.out.println("Error al cargar las propiedades de configuracion: " + e.getMessage());
            System.out.println("Generador: " + getClass().getName());
            e.printStackTrace();
        }
    }

    public static final class DefaultConfiguration extends ConfigurationFactory {

        private static final DefaultConfiguration instance = Initialize();

        @Override
        public String GetKeyValue(String key) {
            return getProperties().getProperty(key, "");
        }

        private static final DefaultConfiguration Initialize() {
            if (instance == null) {
                return new DefaultConfiguration();
            }
            return instance;
        }

        public static <TFactory extends ConfigurationFactory> TFactory GetInstance() {
            return (TFactory) instance;
        }

        @Override
        public boolean HasKey(String key) {
            return getProperties().containsKey(key);
        };
    }
}
