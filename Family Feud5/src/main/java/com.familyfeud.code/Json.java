package com.familyfeud.code;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Json {
    
    private static ObjectMapper objectMapper = getDefaultObjectMapper();

    /**
    * Configures object mapper
    * @return The object mapper
    */
    private static ObjectMapper getDefaultObjectMapper() {
        ObjectMapper defaultObjectMapper = new ObjectMapper();
        defaultObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return defaultObjectMapper;
    }

    /**
    * Returns the JsonNode (From Jackson library)
    * @param JSONL string
    * @return JsonNode
    */
    public static JsonNode parse(String src) throws JsonProcessingException {
        return objectMapper.readTree(src);
    }

}
