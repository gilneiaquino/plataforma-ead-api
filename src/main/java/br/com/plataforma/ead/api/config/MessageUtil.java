package br.com.plataforma.ead.api.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MessageUtil {
    private static final Properties messages = new Properties();

    static {
        try (InputStream stream = MessageUtil.class.getClassLoader().getResourceAsStream("messages.properties")) {
            if (stream != null) {
                messages.load(stream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getMessage(String key) {
        return messages.getProperty(key);
    }
}
