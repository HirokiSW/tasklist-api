package com.hiroki.tasklist.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;

@JsonPropertyOrder({
        "id", "title", "description",
        "complete", "dueDate",
        "createdAt", "updatedAt"
})
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private boolean complete;
    private LocalDate dueDate;
    private LocalDate createdAt;
    private LocalDate updatedAt;

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
    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }
    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
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
