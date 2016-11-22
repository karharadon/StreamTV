package lastochkin.streamTV.helper;

import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static final String PROP_FILE = "/config.properties";

    public static String getProperty(String name) {
        Properties properties = new Properties();
        try {
            properties.load(ConfigProperties.class.getResourceAsStream(PROP_FILE));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = null;

        if (name != null) {
            value = properties.getProperty(name);
        }
        return value;
    }
}