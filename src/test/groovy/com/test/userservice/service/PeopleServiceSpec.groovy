package com.test.userservice.service

import com.test.userservice.repository.PeopleRepository
import com.test.userservice.repository.entity.Person
import com.test.userservice.service.impl.PeopleServiceImpl
import com.test.userservice.util.generator.IdGenerator
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import spock.lang.Specification

import java.time.LocalDate

class PeopleServiceSpec extends Specification {

    PeopleService peopleService
    PeopleRepository peopleRepository
    IdGenerator idGenerator
    Person person

    def setup() {
        person = Person.builder().name("john").age(23)
                .personId("personId")
                .email("john@test.com")
                .birthDate(LocalDate.of(1988, 12,11))
                .build()
        peopleRepository = Mock(PeopleRepository)
        idGenerator = new IdGenerator()
        peopleService = new PeopleServiceImpl(peopleRepository, idGenerator)

    }

    def "should return persons" () {
        given:
            List<Person> persons = [person]
            Page<Person> personPageable = new PageImpl<Person>(persons)

        when:
            def result = peopleService.findAll(_ as Pageable)

        then:
            1 * peopleRepository.findAll(_ as Pageable) >> personPageable
            result.totalPages == 1
            result.totalElements == 1
            def p = result.content.get(0)
            person.birthDate == p.birthDate
            person.age == p.age
            person.name == p.name

    }

    def "should return person by id" () {
        given:
            def personId = "personId"
        when:
            def result  = peopleService.findPersonById(personId)

        then:
            1 * peopleRepository.findByPersonId(personId) >> person
            person.birthDate == result.birthDate
            person.age == result.age
            person.name == result.name
    }

    def "should update person" () {
        given:
            def personId = "personId"
        when:

            def result  = peopleService.updatePerson(personId, person)

        then:
            1 * peopleRepository.findByPersonId(personId) >> person
            1 * peopleRepository.save(_ as Person) >> person
            person.birthDate == result.birthDate
            person.age == result.age
            person.name == result.name
    }


    def "should delete person" () {
        given:
            def personId = "personId"
        when:
            peopleService.deletePerson(personId)

        then:
            1 * peopleRepository.findByPersonId(personId) >> person
            1 * peopleRepository.delete(person)
    }

    def "should throw error if person does not exist"() {
        given:
            def personId = "personId"

        when:
            peopleService.deletePerson(personId)

        then:
            1 * peopleRepository.findByPersonId(personId) >> null
            thrown(RuntimeException)

    }

    def "should throw error if person is null for update" () {
        given:
            def personId = "personId"

        when:
            peopleService.updatePerson(personId, person)

        then:
            1 * peopleRepository.findByPersonId(personId) >> null
            thrown(RuntimeException)
    }

}
