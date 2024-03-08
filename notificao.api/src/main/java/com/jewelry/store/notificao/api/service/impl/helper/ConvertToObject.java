package com.jewelry.store.notificao.api.service.impl.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ConvertToObject {

    public Map<String, Object> convert(String jsonS){
        try{
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(jsonS, Map.class);
            return map;
        } catch (JsonProcessingException e){
            return null;
        }
    }
}
