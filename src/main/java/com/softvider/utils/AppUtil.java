package com.softvider.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;

@Slf4j
public class AppUtil {
    public static final ObjectMapper objectMapper = new ObjectMapper();


    public static String toJSON(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException ex) {
            log.error("IOException: " + ex.getMessage());
            return null;
        }
    }

}
