package com.test.userservice.facade.impl;

import com.test.userservice.facade.PeopleFacade;
import com.test.userservice.repository.entity.Person;
import com.test.userservice.service.PeopleService;
import com.test.userservice.service.dto.PersonDto;
import com.test.userservice.service.dto.PersonPage;
import com.test.userservice.util.mapper.PeopleMapper;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PeopleFacadeImpl implements PeopleFacade {


  private PeopleService peopleService;
  private PeopleMapper peopleMapper;

  @Autowired
  public PeopleFacadeImpl(PeopleService peopleService, PeopleMapper peopleMapper) {
    this.peopleService = peopleService;
    this.peopleMapper = peopleMapper;
  }

  @Override
  public PersonDto findPersonById(String personId) {
    final Person person = this.peopleService.findPersonById(personId);
    return this.peopleMapper.toPersonDto(person);
  }

  @Override
  public PersonPage findAll(Pageable pageable) {
    final Page<Person> all = this.peopleService.findAll(pageable);
    List<PersonDto> personDtos = all.getContent().stream()
        .map(p -> this.peopleMapper.toPersonDto(p)).collect(Collectors.toList());
    return PersonPage.builder().people(personDtos)
        .totalElement(all.getTotalElements())
        .totalPages(all.getTotalPages()).build();
  }

  @Override
  public PersonDto updatePerson(String personId, PersonDto personDto) {
    personDto.validate();
    final Person person = this.peopleMapper.toPerson(personDto);
    final Person res = peopleService.updatePerson(personId, person);
    return this.peopleMapper.toPersonDto(res);
  }

  @Override
  public void deletePerson(String id) {
    this.peopleService.deletePerson(id);
  }

  @Override
  public PersonDto createPerson(PersonDto personDto) {
    personDto.validate();
    final Person person = this.peopleMapper.toPerson(personDto);
    return this.peopleMapper.toPersonDto(this.peopleService.createPerson(person));
  }
}
