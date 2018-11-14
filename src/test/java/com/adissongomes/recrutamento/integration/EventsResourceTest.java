package com.adissongomes.recrutamento.integration;

import com.adissongomes.recrutamento.domains.issue.Event;
import com.adissongomes.recrutamento.domains.issue.Issue;
import com.adissongomes.recrutamento.domains.issue.IssueRepository;
import com.adissongomes.recrutamento.resources.requests.EventPayload;
import com.adissongomes.recrutamento.resources.requests.IssuePayload;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventsResourceTest {

	@Autowired
	private MockMvc mvc;
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private IssueRepository issueRepository;

	@Test
	@Transactional
	public void testEventsByIssue() throws Exception {
		Event editedEvent = Event.builder()
				.action("edited")
				.id(1L)
				.createdAt(LocalDateTime.now())
				.build();

		Issue issue = Issue.builder()
				.id(1L)
				.title("Title 1")
				.url("http://abc.com")
				.createdAt(LocalDateTime.now())
				.events(Arrays.asList(editedEvent)).build();
		issueRepository.save(issue);

		mvc.perform(get("/issues/1/events"))
				.andDo(result -> log.info(result.getResponse().getContentAsString()))
				.andExpect(MockMvcResultMatchers.status().isOk());

	}

	@Test
	public void testEventsByInexistentIssue() throws Exception {

		mvc.perform(get("/issues/100/events")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andDo(result -> log.info("Result: {}", result.getResponse().getContentAsString()))
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
