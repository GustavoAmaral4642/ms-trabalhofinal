package com.jewelry.store.user.api.service.impl;

import com.jewelry.store.user.api.domain.User;
import com.jewelry.store.user.api.repository.UserRepository;
import com.jewelry.store.user.api.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends GenericServiceImpl<User, Long, UserRepository> implements UserService {

    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }
}