package com.jewelry.store.user.api.controller;

import com.jewelry.store.user.api.domain.User;
import com.jewelry.store.user.api.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/user")
public class UserController extends GenericController<User>{

    public UserController(UserService service){
        super(service);
    }
}
