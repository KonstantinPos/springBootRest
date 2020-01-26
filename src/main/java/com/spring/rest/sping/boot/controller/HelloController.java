package com.spring.rest.sping.boot.controller;

import com.spring.rest.sping.boot.model.User;
import com.spring.rest.sping.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {


    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/")
    public String index() {
        userRepository.save(new User(2L, "skhdfjk","jsdhaj"));
        return "Greetings from Spring Boot!";
    }
}
