package com.test.userservice.controller;

import com.test.userservice.facade.PeopleFacade;
import com.test.userservice.service.dto.PersonDto;
import com.test.userservice.service.dto.PersonPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("people")
public class PeopleController {

  private PeopleFacade peopleFacade;

  @Autowired
  public PeopleController(PeopleFacade peopleFacade) {
    this.peopleFacade = peopleFacade;
  }

  @GetMapping(value = "/{id}")
  public ResponseEntity<PersonDto> getPerson(@PathVariable("id") String personId) {
    return new ResponseEntity<>(peopleFacade.findPersonById(personId), HttpStatus.OK);
  }


  @GetMapping
  public ResponseEntity<PersonPage> getPersons(Pageable pageable) {
    PersonPage personPage = peopleFacade.findAll(pageable);
    return new ResponseEntity<>(personPage, HttpStatus.OK);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<PersonDto> updatePerson(@PathVariable("id") String personId,
      @RequestBody PersonDto personDto) {
    final PersonDto result = peopleFacade.updatePerson(personId, personDto);
    return new ResponseEntity<>(result, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<PersonDto> createPerson(@RequestBody PersonDto personDto) {
    final PersonDto person = this.peopleFacade.createPerson(personDto);
    return new ResponseEntity<>(person, HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  public void deletePerson(@PathVariable("id") String personId) {
    this.peopleFacade.deletePerson(personId);
  }

}
