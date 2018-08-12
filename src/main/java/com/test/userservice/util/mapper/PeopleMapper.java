package com.test.userservice.util.mapper;

import com.test.userservice.repository.entity.Person;
import com.test.userservice.service.dto.PersonDto;
import org.springframework.stereotype.Component;

@Component
public class PeopleMapper {

  public static PersonDto toPersonDto(Person person) {
    return PersonDto.builder()
        .personId(person.getPersonId())
        .age(person.getAge())
        .birthDate(person.getBirthDate())
        .email(person.getEmail())
        .name(person.getName())
        .build();
  }

  public static Person toPerson(PersonDto personDto) {
    return Person.builder()
        .age(personDto.getAge())
        .birthDate(personDto.getBirthDate())
        .email(personDto.getEmail())
        .name(personDto.getName())
        .build();
  }


}
