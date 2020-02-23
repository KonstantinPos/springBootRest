package com.spring.rest.sping.boot.repository;

import com.spring.rest.sping.boot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.id in ?1")
    List<User> findUserByIds(List<Long> userIds);

    User findByFirstName(String name);

}
