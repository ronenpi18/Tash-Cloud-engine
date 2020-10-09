package com.ofek324.utils.Handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.List;

public class JsonParser {


    public static ObjectMapper mapper = new ObjectMapper();

    public static <T> String objectToJson(T obj) {
        String jsonString = "";

        try {
            jsonString = mapper.writeValueAsString(obj);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonString;
    }

    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        T obj = null;
        try {
            obj = mapper.readValue(jsonString, clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return obj;
    }


}
