package com.test.userservice.facade;

import com.test.userservice.service.dto.PersonDto;
import com.test.userservice.service.dto.PersonPage;
import org.springframework.data.domain.Pageable;

public interface PeopleFacade {

  PersonDto findPersonById(String personId);
  PersonPage findAll(Pageable pageable);
  PersonDto updatePerson(String personId, PersonDto personDto);
  PersonDto createPerson(PersonDto personDto);
  void deletePerson(String personId);

}
