package com.example.springbootex;

import com.example.springbootex.service.PersonService;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureEmbeddedDatabase
class SpringBootOpenApiApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	PersonService personService;

//	@Test
//	public void testEmbeddedDatabase() throws ResourceNotFoundException {
//		Person personOptional = personService.getPersonById(1L);
//
//		assertThat(personOptional).hasValueSatisfying(person -> {
//			assertThat(personOptional.getId()).isNotNull();
//			assertThat(personOptional.getFirstName()).isEqualTo("Dave");
//			assertThat(personOptional.getLastName()).isEqualTo("Syer");
//		});
//	}

}
