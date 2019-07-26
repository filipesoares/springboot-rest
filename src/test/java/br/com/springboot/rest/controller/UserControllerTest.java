package br.com.springboot.rest.controller;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;

import java.nio.charset.Charset;

import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import br.com.springboot.rest.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest extends BaseTest {
	
	@Before
	public void setUp() throws Exception {

		String auth = username + ":" + password;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("UTF-8")));
		authorization = "Basic " + new String(encodedAuth);

		this.headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		resource = "users";

	}
	
	@Test
	public void listAllTest() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/" + resource)
			.headers(headers))
			.andExpect(status().isOk())
			.andExpect(content().string(notNullValue()));
	}
	
	@Test
	public void fetchTest() throws Exception {
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/" + resource)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(new User("Glenn Doe", true, "glenn", password, "glenn@email.com", null)))
				.headers(headers))
				.andExpect(status().isCreated())
				.andExpect(content().string(equalTo(""))).andReturn();
			
		mvc.perform(MockMvcRequestBuilders.get("/" + resource + "/" + result.getResponse().getHeader("Location").split("users/")[1])
			.headers(headers))
			.andExpect(status().isOk())
			.andExpect(content().string(notNullValue()));
		
	}
	
	@Test
	public void createTest() throws Exception {
		
		mvc.perform(MockMvcRequestBuilders.post("/" + resource)
			.contentType(MediaType.APPLICATION_JSON)
			.content(json(new User("Alex Silva", true, "alex", password, "alex@email.com", null)))
			.headers(headers))
			.andExpect(status().isCreated())
			.andExpect(content().string(equalTo("")))
			.andExpect(header().exists("Location"));
	}
	
	
	
	@Test
	public void updateTest() throws Exception {
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/" + resource)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(new User("Josh Jones", true, "josh", password, "josh@email.com", null)))
				.headers(headers))
				.andExpect(status().isCreated())
				.andExpect(content().string(equalTo(""))).andReturn();
		
		mvc.perform(MockMvcRequestBuilders.put("/" + resource + "/" + result.getResponse().getHeader("Location").split("users/")[1])
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(new User("Josh Jones", true, "josh", password, "josh.jones@email.com", null)))
				.headers(headers))
				.andExpect(status().isOk())
				.andExpect(content().string(notNullValue()));
	}
	
	@Test
	public void deleteTest() throws Exception {
		
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post("/" + resource)
				.contentType(MediaType.APPLICATION_JSON)
				.content(json(new User("Jhon Snow", true, "snow", password, "snow@email.com", null)))
				.headers(headers))
				.andExpect(status().isCreated())
				.andExpect(content().string(equalTo(""))).andReturn();
		
		mvc.perform(MockMvcRequestBuilders.delete("/" + resource + "/" + result.getResponse().getHeader("Location").split("users/")[1])
				.contentType(MediaType.APPLICATION_JSON)
				.headers(headers))
				.andExpect(status().isNoContent())
				.andExpect(content().string(equalTo("")));		
		
	}



}
