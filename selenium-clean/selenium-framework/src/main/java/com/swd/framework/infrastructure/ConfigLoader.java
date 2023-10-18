package com.swd.framework.infrastructure;

import com.swd.framework.util.PropertyUtils;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static volatile ConfigLoader configLoader;

    private static final Object locker = new Object();

    private ConfigLoader(){
        String env = System.getProperty("env", String.valueOf(EnvType.STAGE));

        switch (EnvType.valueOf(env)) {
            case STAGE: {
                this.properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
                break;
            }
            case PRODUCTION: {
                this.properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            }
            default:
                throw new IllegalStateException("Invalid env type: " + env);
        }
    }

    public static ConfigLoader getInstance(){
        ConfigLoader c = configLoader;

        if(c == null){
            synchronized (locker) {
                c = configLoader;

                if(c == null)
                    configLoader = new ConfigLoader();
            }
        }

        return configLoader;
    }

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null) return prop;
        else throw new RuntimeException("property baseUrl is not specified in the stg_config.properties file");
    }

    public String getUsername(){
        String prop = properties.getProperty("username");
        if(prop != null) return prop;
        else throw new RuntimeException("property username is not specified in the stg_config.properties file");
    }

    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null) return prop;
        else throw new RuntimeException("property password is not specified in the stg_config.properties file");
    }

    public String getDefaulBrowser(){
        String prop = properties.getProperty("browser");
        if(prop != null) return prop;
        else throw new RuntimeException("property browser is not specified in the stg_config.properties file");
    }
}
