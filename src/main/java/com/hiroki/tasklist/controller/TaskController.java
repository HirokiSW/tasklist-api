package com.hiroki.tasklist.controller;

import com.hiroki.tasklist.dto.CreateTaskRequest;
import com.hiroki.tasklist.dto.TaskResponse;
import com.hiroki.tasklist.dto.UpdateTaskRequest;
import com.hiroki.tasklist.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/filter/complete")
    public ResponseEntity<List<TaskResponse>> getTasksByCompletion(@RequestParam(required = false, defaultValue = "true") boolean complete) {
        return ResponseEntity.ok(taskService.getTasksByCompletion(complete));
    }

    @GetMapping("/sort/due")
    public ResponseEntity<List<TaskResponse>> getAllTasksSortedByDueDate() {
        return ResponseEntity.ok(taskService.getTasksSortedByDueDate());
    }

    @GetMapping("sort/created")
    public ResponseEntity<List<TaskResponse>> getAllTasksSortedByCreationDate() {
        return ResponseEntity.ok(taskService.getTasksSortedByCreationDate());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {
        return ResponseEntity.status(201).body(taskService.createTask(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable Long id, @Valid @RequestBody UpdateTaskRequest request) {
        return ResponseEntity.ok(taskService.updateTaskById(id, request));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<TaskResponse> completeTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.completeTaskById(id));
    }

    @PutMapping("/{id}/undo")
    public ResponseEntity<TaskResponse> undoCompleteTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.undoCompleteTaskById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TaskResponse> deleteTask(@PathVariable Long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.noContent().build();
    }
}
