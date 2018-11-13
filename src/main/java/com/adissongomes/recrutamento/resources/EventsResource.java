package com.adissongomes.recrutamento.resources;

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
    public ResponseEntity hook(@PathVariable("issueId") Long issueId) {
        return ResponseEntity.ok(this.service.fetchEvents(issueId));
    }

}
