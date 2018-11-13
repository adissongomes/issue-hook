package com.adissongomes.recrutamento.resources;

import com.adissongomes.recrutamento.resources.requests.EventPayload;
import com.adissongomes.recrutamento.services.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@RestController
public class HookResource {

    private IssueService service;

    public HookResource(IssueService service) {
        this.service = service;
    }

    @PostMapping("/events")
    public ResponseEntity hook(@NotNull @RequestBody EventPayload payload) {
        log.info("Evento recebido: {}", payload.toString());
        this.service.saveEvent(payload);
        return ResponseEntity.ok().build();
    }

}
