package com.spring.rest.sping.boot.controller;

import com.spring.rest.sping.boot.model.User;
import com.spring.rest.sping.boot.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserRestControllerIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService service;

    @Test
    public void givenUsers_whenGetEmployees_thenReturnJsonArray()
            throws Exception {
        User user = new User("alex", "Ivanov");
        List<User> allUsers = Collections.singletonList(user);
        given(service.findAll()).willReturn(allUsers);
        mvc.perform(get("/api/all-users")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].firstName", is(user.getFirstName())));
    }
}
