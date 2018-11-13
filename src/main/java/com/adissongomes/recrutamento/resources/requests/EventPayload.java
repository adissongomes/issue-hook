package com.adissongomes.recrutamento.resources.requests;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"action", "issue"})
public class EventPayload {
    private IssuePayload issue;
    private String action;
}