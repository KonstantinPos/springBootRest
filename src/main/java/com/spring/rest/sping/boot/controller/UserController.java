package com.spring.rest.sping.boot.controller;

import com.spring.rest.sping.boot.model.User;
import com.spring.rest.sping.boot.service.UserService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private Job userJob;
    private JobLauncher simpleJobLauncher;

    @Autowired
    public UserController(UserService userService, Job userJob, JobLauncher simpleJobLauncher) {
        this.userService = userService;
        this.userJob = userJob;
        this.simpleJobLauncher = simpleJobLauncher;
    }

    @GetMapping("all-users")
    public List<User> getAll() {
        return userService.findAll();
    }

    @GetMapping("user/{userId}")
    public Optional<User> getUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @GetMapping("users-ids")
    public List<User> getUserByIds(@RequestParam List<Long> userIds) {
        return userService.getUserByIds(userIds);
    }

    @PostMapping("add-user")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "Add user with id = " + user.getId();
    }

    @PutMapping("update-user")
    public String updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return "Update user with id = " + user.getId();
    }

    @DeleteMapping("delete-user")
    public String deleteUser(@RequestBody User user) {
        userService.deleteUser(user);
        return "Delete user with id = " + user.getId();
    }

    @PostMapping(value = "/job")
    public String runJob() throws JobParametersInvalidException,
            JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
        jobParametersBuilder.addDate("date", new Date(), true);
        simpleJobLauncher.run(userJob, jobParametersBuilder.toJobParameters());
        return String.format("Job userJob submitted successfully.");
    }
}
