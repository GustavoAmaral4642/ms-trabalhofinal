package com.jewelry.store.product.api.service.helper;

import com.google.gson.Gson;
import com.jewelry.store.product.api.domain.User;
import org.springframework.stereotype.Component;

@Component
public class JsonToUser {

    public User convert(String json){
        Gson gson = new Gson();
        return gson.fromJson(json, User.class);
    }
}
