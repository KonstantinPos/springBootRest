package com.spring.rest.sping.boot.repository;

import com.spring.rest.sping.boot.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "persons", path = "persons")
public interface PersonRepository extends CrudRepository<Person, Long> {
}
