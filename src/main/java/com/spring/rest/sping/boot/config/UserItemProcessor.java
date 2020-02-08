package com.spring.rest.sping.boot.config;

import com.spring.rest.sping.boot.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class UserItemProcessor implements ItemProcessor<User, User> {
    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    @Override
    public User process(User user) {
        final String firstName = user.getFirstName().toUpperCase();
        final String lastName = user.getLastName().toUpperCase();
        final User transformedUser = new User(firstName, lastName);
        log.info("Converting (" + user + ") into (" + transformedUser + ")");
        return transformedUser;
    }
}
