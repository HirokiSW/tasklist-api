package com.hiroki.tasklist.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    private boolean complete = false;

    private LocalDate dueDate;

    @Column(updatable = false)
    private LocalDate createdAt;

    private LocalDate updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDate.now();
        this.updatedAt = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDate.now();
    }


    public void setId(Long id) {
        this.id = id;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setComplete(boolean complete) {
        this.complete = complete;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public boolean isComplete() {
        return complete;
    }
    public LocalDate getDueDate() {
        return dueDate;
    }
    public LocalDate getCreatedAt() {
        return createdAt;
    }
    public LocalDate getUpdatedAt() {
        return updatedAt;
    }
}
