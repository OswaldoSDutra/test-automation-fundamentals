package com.swd.framework.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtils {

    public static <T> T objectFromJson(String jsonPath, Class<T> returnTypeClass) throws IOException {
        InputStream input = JacksonUtils.class.getClassLoader().getResourceAsStream(jsonPath);
        ObjectMapper obj = new ObjectMapper();
        return obj.readValue(input, returnTypeClass);
    }
}
