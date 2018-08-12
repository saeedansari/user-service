package com.test.userservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.test.userservice.controller.PeopleController
import com.test.userservice.facade.PeopleFacade
import com.test.userservice.service.dto.PersonDto
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup

class PeopleControllerSpec extends Specification {

    PeopleFacade peopleFacade
    PeopleController peopleController
    MockMvc mockMvc
    PersonDto personDto
    ObjectMapper objectMapper

    def setup() {
        objectMapper = new ObjectMapper()
        objectMapper.registerModule(new JavaTimeModule())
        def date = LocalDate.now()
        personDto = PersonDto.builder().name("john").age(23)
                .personId("personId")
                .email("john@test.com")
                .birthDate(date)
                .build()
        peopleFacade = Mock(PeopleFacade)
        peopleController = new PeopleController(peopleFacade)
        mockMvc = standaloneSetup(peopleController).build()
    }


    def "PeopleController should create person"() {
        when:
            def rsp = mockMvc.perform(post('/people' )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(status().isCreated())
                .andReturn()

        then:
            1 * peopleFacade.createPerson(personDto) >> personDto
            def result = objectMapper.readValue(rsp.response.contentAsString, PersonDto)
            result == personDto
    }

    def "PeopleController should update person"() {
        given:
            def personId = "personId"
        when:
        def rsp = mockMvc.perform(put('/people/' + personId )
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personDto)))
                .andExpect(status().isOk())
                .andReturn()

        then:
            1 * peopleFacade.updatePerson(personId, personDto) >> personDto
            def result = objectMapper.readValue(rsp.response.contentAsString, PersonDto)
            result == personDto
    }

    def "PeopleController should find person"() {
        given:
            def personId = "personId"
        when:
            def rsp = mockMvc.perform(get('/people/' + personId )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()

        then:
            1 * peopleFacade.findPersonById(personId) >> personDto
            def result = objectMapper.readValue(rsp.response.contentAsString, PersonDto)
            result == personDto
    }

    def "PeopleController should delete person"() {
        given:
        def personId = "personId"
        when:
            mockMvc.perform(delete('/people/' + personId )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn()

        then:
            1 * peopleFacade.deletePerson(personId)
    }


}
