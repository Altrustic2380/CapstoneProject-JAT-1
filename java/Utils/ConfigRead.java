package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigRead {
    private static Properties properties = new Properties();

    // Load properties when the class is initialized
    static {
        try {
            FileInputStream file = new FileInputStream("./src/test/resources/config.properties");
            properties.load(file);
            file.close();
        } catch (IOException e) {
            System.out.println("Failed to load config.properties: " + e.getMessage());
        }
    }

    // Method to get property values
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
