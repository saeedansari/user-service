package com.test.userservice.service;

import com.test.userservice.repository.entity.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PeopleService {

  Person findPersonById(String personId);
  Page findAll(Pageable pageable);
  Person updatePerson(String personId, Person person);
  void deletePerson(String personId);
  Person createPerson(Person person);


}
