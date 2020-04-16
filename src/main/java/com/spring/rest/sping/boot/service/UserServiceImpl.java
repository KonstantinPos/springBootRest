package com.spring.rest.sping.boot.service;

import com.spring.rest.sping.boot.model.User;
import com.spring.rest.sping.boot.repository.UserPageableRepository;
import com.spring.rest.sping.boot.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private UserPageableRepository userPageableRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserPageableRepository userPageableRepository) {
        this.userRepository = userRepository;
        this.userPageableRepository = userPageableRepository;
    }

    public UserServiceImpl() {
    }

    @Override
    public User findByFirstName(String name) {
        return userRepository.findByFirstName(name);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public List<User> getUserByIds(List<Long> userIds) {
        return userRepository.findUserByIds(userIds);
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public List<User> findAllPageable(Pageable pageable) {
        return userPageableRepository.findAllPageable(pageable);
    }
}
