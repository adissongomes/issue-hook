package com.adissongomes.recrutamento.resources;

import com.adissongomes.recrutamento.exceptions.IssueNotFoundException;
import com.adissongomes.recrutamento.services.IssueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/issues")
public class EventsResource {

    private IssueService service;

    public EventsResource(IssueService service) {
        this.service = service;
    }

    @GetMapping("/{issueId}/events")
    public ResponseEntity fetchEventsByIssue(@PathVariable("issueId") Long issueId) {
        try {
            return ResponseEntity.ok(this.service.fetchEvents(issueId));
        } catch (IssueNotFoundException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

}
