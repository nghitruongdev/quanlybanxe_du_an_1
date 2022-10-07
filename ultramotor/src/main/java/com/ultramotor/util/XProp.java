package com.ultramotor.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * Read properties from application.properties
 */
public class XProp {

    static Properties prop = loadProperties();

    public static String getProperty(String key) {
        return prop.getProperty(key);
    }

    public static String getProperty(String key, String defaultValue) {
        return prop.getProperty(key, defaultValue);
    }

    private static Properties loadProperties() {
        String userDir = System.getProperty("user.dir");
        File propFile = Paths.get(userDir, "ultramotor", "application.properties").toFile();

        Properties prop = new Properties();
        try (InputStream input = new FileInputStream(propFile)) {
            prop.load(input);
            return prop;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
