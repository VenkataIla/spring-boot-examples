//package com.example.springbootex;
//
//import com.example.springbootex.modal.Person;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.*;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
//public class SpringRestClient {
//
//    private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://localhost:8080/api/v1/employees";
//    private static final String GET_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";
//    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees";
//    private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";
//    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://localhost:8080/api/v1/employees/{id}";
//    @Autowired
//    private  RestTemplate restTemplate;
//
//    public static void main(String[] args) {
//        SpringRestClient springRestClient = new SpringRestClient();
//
//        // Step1: first create a new employee
//        springRestClient.createPerson();
//
//        // Step 2: get new created employee from step1
//        springRestClient.getPersonById();
//
//        // Step3: get all employees
//        springRestClient.getPersons();
//
//        // Step4: Update employee with id = 1
//        springRestClient.updatePerson();
//
//        // Step5: Delete employee with id = 1
//        springRestClient.deletePerson();
//    }
//
//    private void getPersons() {
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity< String > entity = new HttpEntity < String > ("parameters", headers);
//
//        ResponseEntity< String > result = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity,
//            String.class);
//
//        System.out.println(result);
//    }
//
//    private void getPersonById() {
//
//        Map< String, String > params = new HashMap<>();
//        params.put("id", "1");
//
//        RestTemplate restTemplate = new RestTemplate();
//        Person result = restTemplate.getForObject(GET_EMPLOYEE_ENDPOINT_URL, Person.class, params);
//
//        System.out.println(result);
//    }
//
//    private void createPerson() {
//
//        Person newPerson = new Person("admin", "admin", "admin@gmail.com");
//
//        RestTemplate restTemplate = new RestTemplate();
//        Person result = restTemplate.postForObject(CREATE_EMPLOYEE_ENDPOINT_URL, newPerson, Person.class);
//
//        System.out.println(result);
//    }
//
//    private void updatePerson() {
//        Map < String, String > params = new HashMap < String, String > ();
//        params.put("id", "1");
//        Person updatedPerson = new Person("admin123", "admin123", "admin123@gmail.com");
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.put(UPDATE_EMPLOYEE_ENDPOINT_URL, updatedPerson, params);
//    }
//
//    private void deletePerson() {
//        Map < String, String > params = new HashMap < String, String > ();
//        params.put("id", "1");
//        RestTemplate restTemplate = new RestTemplate();
//        restTemplate.delete(DELETE_EMPLOYEE_ENDPOINT_URL, params);
//    }
//}