package utils;

import java.io.IOException;
import java.util.Properties;

public class Configs {
    private static final String PROPERTIES_FILENAME = "/userinfo.properties";
    private static Properties properties;

    private Configs() {
    }

    public static String getValueByKey(String key) {
        if (properties == null) {
            loadProperties();
        }
        return properties.get(key).toString();
    }

    private static Properties loadProperties() {
        try {
            properties = new Properties();
            properties.load(Configs.class.getResourceAsStream(PROPERTIES_FILENAME));
        } catch (IOException e) {
        }
        return properties;
    }
}
