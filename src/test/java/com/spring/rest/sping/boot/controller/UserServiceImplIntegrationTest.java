package com.spring.rest.sping.boot.controller;

import com.spring.rest.sping.boot.model.User;
import com.spring.rest.sping.boot.repository.UserRepository;
import com.spring.rest.sping.boot.service.UserService;
import com.spring.rest.sping.boot.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class UserServiceImplIntegrationTest {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        User user = new User("Ivan", "Ivanov");
        Mockito.when(userRepository.findByFirstName(user.getFirstName()))
                .thenReturn(user);
    }

    @Test
    public void whenValidName_thenUserShouldBeFound() {
        String name = "Ivan";
        User found = userService.findByFirstName(name);
        assertThat(found.getFirstName())
                .isEqualTo(name);
    }
}
