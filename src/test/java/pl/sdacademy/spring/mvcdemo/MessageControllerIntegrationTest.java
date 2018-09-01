package pl.sdacademy.spring.mvcdemo;

import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet
	.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerIntegrationTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void should_RedirectToLoginPage_When_AccessSecuredEndpoint()
		throws Exception {
		// given
		String endpoint = "/secured";

		// when
		String redirectedUrl = mockMvc.perform(MockMvcRequestBuilders
			.get(endpoint)).andReturn().getResponse()
			.getRedirectedUrl();

		// then
		mockMvc.perform(MockMvcRequestBuilders.get(redirectedUrl))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.view().name("login"));
	}

	@Test
	public void should_PassLoginForm_When_RightCredentialsArePassed()
		throws Exception {
		// given
		String endpoint = "/login";

		// when
		mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
			// user param
			.param("user", "goobar")
			// password param
			.param("pass", "password"))
			// then
			// redirects to main page
			.andExpect(MockMvcResultMatchers.redirectedUrl("/"));
	}

	@Test
	public void should_FailLoginForm_When_BadCredentialsArePassed() throws
		Exception {
		// given
		String endpoint = "/login";

		// when
		mockMvc.perform(MockMvcRequestBuilders.post(endpoint)
			// user param
			.param("user", "badUser")
			// password param
			.param("pass", "badPassword"))
			// then
			// redirects to login again with ?error param
			.andExpect(MockMvcResultMatchers.redirectedUrl
				("/login?error"));
	}

	@Test
	public void should_ReturnOkStatus_When_GetMessageEndpoint() throws
		Exception {
		// given
		String endpoint = "/message";

		// when
		mockMvc.perform(MockMvcRequestBuilders.get(endpoint))

			// then
			.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void should_CreateEmptyMessageModel_When_GetMessageEndpoint()
		throws Exception {
		// given
		String endpoint = "/message";

		// when
		mockMvc.perform(MockMvcRequestBuilders.get(endpoint))

			// then
			.andExpect(MockMvcResultMatchers.model()
				.attributeExists("message"));
	}

	@Test
	public void should_CreateMessage_When_PostMessageEndpoint() throws
		Exception {
		// given
		String endpoint = "/message";

		// when
		mockMvc.perform(MockMvcRequestBuilders.post(endpoint).param
			("content", "msg content"))

			// then
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.model().attribute
				("message", Matchers.
			hasProperty("content", Matchers.is("msg content"))))
			.andExpect(MockMvcResultMatchers.view().name
				("show-message"));
	}
}
