package com.adissongomes.recrutamento.resources.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RepositoryPayload {

    private Integer id;
    private Owner owner;
    @JsonProperty("full_name")
    private String fullName;

}