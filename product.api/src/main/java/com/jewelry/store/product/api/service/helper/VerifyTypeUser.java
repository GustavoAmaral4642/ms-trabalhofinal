package com.jewelry.store.product.api.service.helper;

import com.jewelry.store.product.api.domain.User;
import org.springframework.stereotype.Component;

@Component
public class VerifyTypeUser {

    public boolean verify(User user){
        return user.getTypeuser().equals("Admin");
    }
}
