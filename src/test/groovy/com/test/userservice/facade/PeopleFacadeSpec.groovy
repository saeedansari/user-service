package com.test.userservice.facade

import com.test.userservice.facade.impl.PeopleFacadeImpl
import com.test.userservice.repository.entity.Person
import com.test.userservice.service.PeopleService
import com.test.userservice.util.exception.UserServiceException
import com.test.userservice.util.mapper.PeopleMapper
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import spock.lang.Specification

import java.time.LocalDate

class PeopleFacadeSpec extends Specification {

    PeopleService peopleService
    PeopleFacade peopleFacade
    PeopleMapper peopleMapper
    Person person

    def setup() {
        person = Person.builder().name("john").age(23)
                .personId("personId")
                .email("john@test.com")
                .birthDate(LocalDate.of(1988, 12,11))
                .build()
        peopleService = Mock(PeopleService)
        peopleMapper = new PeopleMapper()
        peopleFacade = new PeopleFacadeImpl(peopleService, peopleMapper)

    }

    def "should return persons" () {
        given:
            List<Person> persons = [person]
           Page<Person> personPageable = new PageImpl<Person>(persons)

        when:
            def result = peopleFacade.findAll(_ as Pageable)

        then:
            1 * peopleService.findAll(_ as Pageable) >> personPageable
            result.totalPages == 1
            result.totalElement == 1
            def p = result.people.get(0)
            person.birthDate == p.birthDate
            person.age == p.age
            person.name == p.name

    }

    def "should return person by id" () {
        given:
            def personId = "personId"
        when:
            def result  = peopleFacade.findPersonById(personId)

        then:
            1 * peopleService.findPersonById(personId) >> person
            person.birthDate == result.birthDate
            person.age == result.age
            person.name == result.name
    }

    def "should update person" () {
        given:
            def personId = "personId"
        when:

            def result  = peopleFacade.updatePerson(personId, peopleMapper.toPersonDto(person))

        then:
            1 * peopleService.updatePerson(personId, _ as Person) >> person
            person.birthDate == result.birthDate
            person.age == result.age
            person.name == result.name
    }


    def "should delete person" () {
        given:
            def personId = "personId"
        when:
            peopleFacade.deletePerson(personId)

        then:
            1 * peopleService.deletePerson(personId)
    }

    def "should throw error if request invalid" () {
        given:
            def personId = "personId"
            person.name = null
        when:
            peopleFacade.updatePerson(personId, peopleMapper.toPersonDto(person))

        then:
            def e = thrown(UserServiceException)
            e.httpStatus == HttpStatus.UNPROCESSABLE_ENTITY
    }


}
