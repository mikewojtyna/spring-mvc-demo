package pl.sdacademy.spring.mvcdemo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet
	.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request
	.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request
	.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request
	.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result
	.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result
	.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result
	.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GreetingControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void getGreeting() throws Exception {
		mockMvc.perform(get("/greeting")).andExpect(status().isOk())
			.andExpect(model().attributeExists("greeting"))
			.andExpect(model().attribute("greeting", hasProperty
				("content", isEmptyOrNullString()))).andDo
			(print());
	}

	@Test
	public void postGreeting() throws Exception {
		mockMvc.perform(post("/greeting").param("content", "hello " +
			"world!")).andExpect(status().isOk()).andExpect(model
			().attributeExists("greeting")).andExpect(model()
			.attribute("greeting", hasProperty("content", is
				("hello world!")))).andDo(print());
	}

}
