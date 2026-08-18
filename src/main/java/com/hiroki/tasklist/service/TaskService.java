package com.hiroki.tasklist.service;

import com.hiroki.tasklist.dto.CreateTaskRequest;
import com.hiroki.tasklist.dto.TaskResponse;
import com.hiroki.tasklist.dto.UpdateTaskRequest;
import com.hiroki.tasklist.exception.TaskNotFoundException;
import com.hiroki.tasklist.model.Task;
import com.hiroki.tasklist.repository.TaskRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    private TaskResponse toResponse(Task task) {
        TaskResponse response = new TaskResponse();
        response.setId(task.getId());
        response.setTitle(task.getTitle());
        response.setDescription(task.getDescription());
        response.setComplete(task.isComplete());
        response.setDueDate(task.getDueDate());
        response.setCreatedAt(task.getCreatedAt());
        response.setUpdatedAt(task.getUpdatedAt());
        return response;
    }

    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(task -> toResponse(task))
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getTasksByCompletion(boolean complete) {
        return taskRepository.findByComplete(complete)
                .stream()
                .map(task -> toResponse(task))
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getTasksSortedByDueDate() {
        return taskRepository.findAllByOrderByDueDateAsc()
                .stream()
                .map(task -> toResponse(task))
                .collect(Collectors.toList());
    }

    public List<TaskResponse> getTasksSortedByCreationDate() {
        return taskRepository.findAllByOrderByCreatedAtAsc()
                .stream()
                .map(task -> toResponse(task))
                .collect(Collectors.toList());
    }

    public TaskResponse getTaskById(Long id) {
        Task task = findTask(id);
        return toResponse(task);
    }

    public TaskResponse createTask(CreateTaskRequest request) {
        Task task = new Task();
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDueDate(request.getDueDate());
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse updateTaskById(Long id, @NonNull UpdateTaskRequest request) {
        Task task = findTask(id);

        if (request.getTitle() != null) {
            task.setTitle(request.getTitle());
        }

        if (request.getDescription() != null) {
            task.setDescription(request.getDescription());
        }

        if (request.getDueDate() != null) {
            task.setDueDate(request.getDueDate());
        }

        return toResponse(taskRepository.save(task));
    }

    public void deleteTaskById(Long id) {
        taskRepository.deleteById(id);
    }

    public TaskResponse completeTaskById(Long id) {
        Task task = findTask(id);
        task.setComplete(true);
        return toResponse(taskRepository.save(task));
    }

    public TaskResponse undoCompleteTaskById(Long id) {
        Task task = findTask(id);
        task.setComplete(false);
        return toResponse(taskRepository.save(task));
    }

    private Task findTask(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException(id));
    }
}
