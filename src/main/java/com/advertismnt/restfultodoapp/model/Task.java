package com.advertismnt.restfultodoapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Setter
@Getter
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    private boolean completed = false;
    private String description;
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    private Priority priority = Priority.MEDIUM;

    public Task(String title, String description, LocalDate dueDate, Priority priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public Task() {

    }

}
