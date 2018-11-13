package com.adissongomes.recrutamento.domains.issue;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "action")
@Entity
@Table(name="events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_seq")
    @SequenceGenerator(name="event_seq", sequenceName = "event_seq_id", allocationSize = 1)
    private Long id;

    private String action;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="issue_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_issue"))
    private Issue issue;

}
