package com.adissongomes.recrutamento.integration;

import com.adissongomes.recrutamento.resources.requests.EventPayload;
import com.adissongomes.recrutamento.resources.requests.IssuePayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class HookResourceTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;

	@Test
	public void testHook() throws Exception {

		String json = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(validPayload());
		mvc.perform(post("/events")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testHookEmptyPayload() throws Exception {

		mvc.perform(post("/events")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isBadRequest());

	}

	private EventPayload validPayload() {

		IssuePayload issue = IssuePayload.builder()
				.number(1L)
				.createdAt(LocalDateTime.now())
				.title("Title 1")
				.url("http://abc.com")
				.build();

		return EventPayload.builder()
				.action("edited")
				.issue(issue)
				.build();
	}

}
