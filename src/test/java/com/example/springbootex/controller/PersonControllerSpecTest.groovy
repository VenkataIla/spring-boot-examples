/*
package com.example.springbootex.controller

import com.example.springbootex.BaseIntegrationSpec
import com.example.springbootex.modal.Person
import com.example.springbootex.repo.PersonRepo
import com.example.springbootex.service.PersonService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import spock.lang.Retry
import spock.lang.Unroll

@Unroll
@Retry(count = 0)
class PersonControllerSpecTest extends BaseIntegrationSpec{

    @Autowired
    PersonService personService;

    @Autowired
    PersonRepo personRepo

    def setup(){
        mockMvc = getMockMvcSetup()
    }

    def "save person details"(){
       when:
        def person = mockPersonObject();

        then:
        def mvcResult = mockMvc.perform (post("/person",request))
        .andExpect("Ok")
        .andExpect (matching("success"))
        .andReturn();


        expect:
        mockMvc.perform (post("/person",request)).andExpect(expectedMessage)

        where:
        request             | expectedMessage
        mockPersonObject() | "success"
    }
    @Unroll
    def mockPersonObject(){
        ObjectMapper mapper = new ObjectMapper();
        Person person = mapper.readValue("person/personRequest.json",Person.class)
        //Person person = jsonToObject("person/personRequest.json",Object.class);
        return person;
    }

//    def "Test get the list of persons"()
//    {
//        given:
//        personRepo.findAll(*_) >>
//
//        expect:
//        mockMvc.perform(post("/api/persons",request))
//        .()
//        where:
//        request    | expectedMssage
//        null       |
//    }
//    def getjsonRequest()
//    {
//        def jsonRequest = jsonToObject
//    }

    def "two plus two should equal four"() {
        given:
        int left = 2
        int right = 2

        when:
        int result = left + right

        then:
        result == 4
    }

    def "givenCustomer_whenPostCustomer_thenStatus200"() throws Exception {
        when:
        Person person = mapper.readValue("person/personRequest.json",Person.class)

        then:
        mockMvc.perform(post("/person")
                .contentType(MediaType.APPLICATION_JSON)
                .content(person))
                .andDo(print())
                .andExpect(status().isOk())
    }

}
*/
