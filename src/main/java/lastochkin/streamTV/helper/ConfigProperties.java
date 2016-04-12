package lastochkin.streamTV.helper;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigProperties {
    private static Properties PROPERTIES;

    static {
        PROPERTIES = new Properties();
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            PROPERTIES.load(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("src/main/resources/config.properties not found.");
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

    public static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}