package com.example.springbootex

import com.fasterxml.jackson.databind.ObjectMapper
import io.zonky.test.db.AutoConfigureEmbeddedDatabase
import org.hamcrest.Matchers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.runner.WebApplicationContextRunner
import org.springframework.boot.web.context.WebServerApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.ResultMatcher
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.MultiValueMap
import org.springframework.web.context.WebApplicationContext
import spock.lang.Specification

@ContextConfiguration
@SpringBootTest
@AutoConfigureMockMvc
@WebAppConfiguration
@AutoConfigureEmbeddedDatabase(beanName = "postgresDataSource", provider = AutoConfigureEmbeddedDatabase.DatabaseProvider.ZONKY)
abstract class BaseIntegrationSpec extends Specification {

    @Autowired
    WebServerApplicationContext context;

    static MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();


    MockHttpServletRequestBuilder get(String endPoint, Object ... uriVar) throws Exception{
        return requestBuilder(MockMvcRequestBuilders.get(endPoint, uriVar));
    }

    MockHttpServletRequestBuilder get(String endPoint, MultiValueMap params, Object ... uriVar) throws Exception{
        return requestBuilder(MockMvcRequestBuilders.get(endPoint, uriVar).params(params));
    }

    MockHttpServletRequestBuilder get(String endPoint, Object object) throws Exception{
        return requestBuilder(MockMvcRequestBuilders.get(endPoint).content(JsonOutput.toJson(object)));
    }

    MockHttpServletRequestBuilder post(String endPoint) throws Exception{
        return requestBuilder(MockMvcRequestBuilders.post(endPoint));
    }
    MockHttpServletRequestBuilder post(String endPoint, Object object) throws Exception{
        return requestBuilder(MockMvcRequestBuilders.post(endPoint).content(JsonOutput.toJson(object)));
    }

    MockHttpServletRequestBuilder put(String endPoint, Object object) throws Exception{
        return requestBuilder(MockMvcRequestBuilders.put(endPoint).content(JsonOutput.toJson(object)));
    }


    MockHttpServletRequestBuilder delete(String endPoint, Object object) throws Exception{
        return requestBuilder(MockMvcRequestBuilders.delete(endPoint).content(JsonOutput.toJson(object)));
    }

    MockHttpServletRequestBuilder requestBuilder(MockHttpServletRequestBuilder mockHttpServletRequestBuilder){
        return mockHttpServletRequestBuilder.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .characterEncoding("ctf-8");
    }

    def getMockMvcSetup()
    {
        return MockMvcBuilders.webAppContextSetup(context);
    }
    def <T> T jsonToObject(String fileName, Class<T> clazz){
        return mapper.readValue(getJsonData(fileName),clazz)
    }

    def getJsonData(String fileName){
        return this.class.classLoader.getResource(fileName).text;
    }

    String objectToJson(Object jsonObject){
        return mapper.writeValueAsString(jsonObject)
    }

    protected ResultMatcher matching(expectedMessage){
        jsonPath('$.message', Matchers.containsString(expectedMessage))
    }

    protected ResultMatcher matching(String path,expectedMessage){
        jsonPath(path, Matchers.containsString(expectedMessage))
    }
}
