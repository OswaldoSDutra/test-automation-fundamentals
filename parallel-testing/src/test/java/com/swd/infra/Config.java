package com.swd.infra;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {

    private final String configPath = "config.properties";
    private Properties properties;
    private static final Object lock = new Object();

    private static volatile Config config;

    private Config(){

    }

    private static Config getInstance() throws IOException {
        Config temp = config;

        if(temp == null){
            synchronized (lock){
                temp = config;

                if(temp == null) {
                    config = new Config();
                    config.loadProperties();
                    temp = config;
                }

            }
        }

        return temp;

    }

    private void loadProperties() throws IOException {
        properties = new Properties();
        InputStream str = this.getClass().getClassLoader().getResourceAsStream(configPath);
        properties.load(str);
    }

    public static String getProperty(String property) throws IOException {
        return getInstance().properties.getProperty(property);
    }

}
