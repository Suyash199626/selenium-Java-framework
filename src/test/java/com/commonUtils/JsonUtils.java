package com.commonUtils;

import java.io.InputStream;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

    private static final ObjectMapper objectMapper =
            new ObjectMapper();

    public static JsonNode readJson(String filePath) {

        try {

            InputStream is = JsonUtils.class
                    .getClassLoader()
                    .getResourceAsStream(filePath);

            if (is == null) {

                throw new RuntimeException(
                        "JSON file not found: " + filePath);
            }

            return objectMapper.readTree(is);

        } catch (Exception e) {

            throw new RuntimeException(
                    "Failed to read JSON file: " + filePath,
                    e);
        }
    }
}