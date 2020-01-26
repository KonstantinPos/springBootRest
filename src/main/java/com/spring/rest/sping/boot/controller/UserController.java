package com.spring.rest.sping.boot.controller;

import com.spring.rest.sping.boot.model.User;
import com.spring.rest.sping.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {


    @Autowired
    private UserRepository userRepository;

    @GetMapping("all-users")
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @GetMapping("user/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId);
    }

    @GetMapping("users-ids")
    public List<User> getUserByIds(@RequestParam List<Long> userIds) {
        return userRepository.findUserByIds(userIds);
    }

    @PostMapping("add-user")
    public String adduser(@RequestBody User user) {
        userRepository.save(user);
        return "Add user with id = " + user.getId();
    }

    @PutMapping("update-user")
    public String updateUser(@RequestBody User user) {
        userRepository.save(user);
        return "Update user with id = " + user.getId();
    }

    @DeleteMapping("delete-user")
    public String deleteUser(@RequestBody User user) {
        userRepository.delete(user);
        return "Delete user with id = " + user.getId();
    }
}
