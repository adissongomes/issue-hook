package com.adissongomes.recrutamento.services;

import com.adissongomes.recrutamento.domains.issue.Event;
import com.adissongomes.recrutamento.domains.issue.Issue;
import com.adissongomes.recrutamento.domains.issue.IssueRepository;
import com.adissongomes.recrutamento.exceptions.IssueNotFoundException;
import com.adissongomes.recrutamento.resources.requests.EventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class IssueService {

    private IssueRepository repository;

    public IssueService(IssueRepository repository) {
        this.repository = repository;
    }

    public void saveEvent(EventPayload payload) {

        if (payload == null) {
            log.warn("Payload vazio. Nenhum evento para registrar");
        }

        Issue issue = repository.findById(payload.getIssue().getNumber()).
                orElse(null);

        if (issue != null) {
            issue.setUpdatedAt(payload.getIssue().getUpdatedAt());
        } else {
            issue = Issue.builder()
                    .id(payload.getIssue().getNumber())
                    .title(payload.getIssue().getTitle())
                    .url(payload.getIssue().getUrl())
                    .createdAt(payload.getIssue().getCreatedAt())
                    .updatedAt(payload.getIssue().getUpdatedAt())
                    .build();
        }

        Event event = Event.builder()
                .action(payload.getAction())
                .createdAt(LocalDateTime.now())
                .issue(issue)
                .build();

        issue.getEvents().add(event);

        repository.save(issue);
        log.info("Evento registrado: {}", issue);

    }

    public List<Event> fetchEvents(Long issueId) {
        Issue issue = repository.findById(issueId).orElseThrow(() -> new IssueNotFoundException(issueId));
        return issue.getEvents();
    }

}
