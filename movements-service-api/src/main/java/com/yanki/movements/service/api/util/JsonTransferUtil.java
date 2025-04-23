package com.yanki.movements.service.api.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class JsonTransferUtil {

        private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static String objectToJson(Object object) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);

        // Registrar el módulo para soportar Java 8 LocalDate y otros tipos de fecha/hora
        mapper.registerModule(new JavaTimeModule());

        String jsonString = null;
        try {
            jsonString = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }


    public static <T> T jsonToObject(String json, Class<T> clazz) {
        try {
            return OBJECT_MAPPER.readValue(json, clazz);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON to object: " + e.getMessage(), e);
        }
    }
}
