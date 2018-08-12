package com.test.userservice.repository;

import com.test.userservice.repository.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PeopleRepository extends JpaRepository<Person, Long> {

  Person findByPersonId(String personId);

}
