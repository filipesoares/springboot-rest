package br.com.springboot.rest.controller;

import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.web.servlet.MockMvc;

public class BaseTest {
	
	@Autowired
	protected MockMvc mvc;
	
	protected HttpMessageConverter mappingJackson2HttpMessageConverter;
	protected static String authorization;
	protected static final String username = "admin";
	protected static final String password = "123456";
	protected static String resource;
	protected HttpHeaders headers = new HttpHeaders();
	protected static final LocalDateTime now = LocalDateTime.now();
	
	@Autowired
	void setConverters(HttpMessageConverter<?>[] converters) {

		this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream()
				.filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().orElse(null);

		assertNotNull("the JSON message converter must not be null", this.mappingJackson2HttpMessageConverter);
	}
	
	protected String json(Object o) throws IOException {
		try {
			MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
			this.mappingJackson2HttpMessageConverter.write(o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
			return mockHttpOutputMessage.getBodyAsString();
		} catch (Exception e) {
			throw e;
		}
	}

}
