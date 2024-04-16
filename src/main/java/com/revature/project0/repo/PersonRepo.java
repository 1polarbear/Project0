package com.revature.project0.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.revature.project0.model.Person;

@RepositoryRestResource

public interface PersonRepo extends JpaRepository<Person, Long>{
}
