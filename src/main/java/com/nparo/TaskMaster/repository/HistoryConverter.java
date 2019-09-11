package com.nparo.TaskMaster.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

// sourced: Trevor Dobson https://github.com/trevorjdobson/taskmaster/blob/broken-converter/taskmaster/src/main/java/com/trevorjdobson/taskmaster/models/HistoryItemConverter.java
public class HistoryConverter<T extends Object>
        implements DynamoDBTypeConverter<String, T> {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convert(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException("Unable to parse JSON");
    }

    @Override
    public T unconvert(String object) {
        try {
            T unconvertedObject = objectMapper.readValue(object,
                    new TypeReference<T>() {
                    });
            return unconvertedObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
