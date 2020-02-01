package com.spring.rest.sping.boot.service;

import com.spring.rest.sping.boot.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    User findByFirstName(String name);

    List<User> findAll();

    List<User> getUserByIds(List<Long> userIds);

    Optional<User> findById(Long userId);

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);
}
