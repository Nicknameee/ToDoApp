package io.uwp.digital.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.Clock;
import java.time.ZonedDateTime;

@Entity
@Table(name = "to_do_items")
@Data
public class ToDoItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "issued_at", updatable = false)
    private ZonedDateTime issuedAt = ZonedDateTime.now(Clock.systemUTC());
    @Column(name = "finish_by")
    private ZonedDateTime finishBy;
    @Column(name = "finished_at")
    private ZonedDateTime finishedAt;
}
