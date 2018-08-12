package com.test.userservice.service.impl;

import com.test.userservice.repository.PeopleRepository;
import com.test.userservice.repository.entity.Person;
import com.test.userservice.service.PeopleService;
import com.test.userservice.util.generator.IdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PeopleServiceImpl implements PeopleService {

  private PeopleRepository peopleRepository;
  private IdGenerator idGenerator;

  @Autowired
  public PeopleServiceImpl(PeopleRepository peopleRepository, IdGenerator idGenerator) {
    this.peopleRepository = peopleRepository;
    this.idGenerator = idGenerator;
  }


  @Override
  public Person findPersonById(String personId) {
    final Person person = peopleRepository.findByPersonId(personId);
    if (person == null) {
      log.error("Person with id {} was not found", personId);
      throw new RuntimeException("Person was not found");
    }
    return person;
  }

  @Override
  public Page<Person> findAll(Pageable pageable) {
    return peopleRepository.findAll(pageable);
  }

  @Override
  public Person updatePerson(String personId, Person person) {
    Person foundPerson = this.peopleRepository.findByPersonId(personId);
    if (person == null) {
      log.error("Person with id {} was not found", personId);
      throw new RuntimeException("Person was not found to update");
    }
    foundPerson.setAge(person.getAge());
    foundPerson.setEmail(person.getEmail());
    foundPerson.setName(person.getName());
    foundPerson.setBirthDate(person.getBirthDate());
    return peopleRepository.save(foundPerson);
  }

  @Override
  public void deletePerson(String personId) {
    Person toDelete = this.peopleRepository.findByPersonId(personId);
    if (toDelete == null) {
      log.error("Person with id {} was not found", personId);
      throw new RuntimeException("Person was not found to delete");
    }
    peopleRepository.delete(toDelete);
  }

  @Override
  public Person createPerson(Person person) {
    Person createdPerson = null;
    try {
      person.setPersonId(this.idGenerator.generatePersonId());
      createdPerson = peopleRepository.saveAndFlush(person);
    } catch (Exception e) {
      log.error("Failed to create person ", e.getMessage());
      throw new RuntimeException("Failed to create person");
    }

    return createdPerson;
  }

}
