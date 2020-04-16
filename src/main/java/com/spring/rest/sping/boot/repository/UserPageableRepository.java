package com.spring.rest.sping.boot.repository;

import com.spring.rest.sping.boot.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface UserPageableRepository extends PagingAndSortingRepository<User, Long> {

    @Query(value = "select * from mcs.user", nativeQuery = true)
    List<User> findAllPageable(Pageable pageable);
}
