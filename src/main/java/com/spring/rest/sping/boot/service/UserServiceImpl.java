package com.spring.rest.sping.boot.service;

import com.spring.rest.sping.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

}
