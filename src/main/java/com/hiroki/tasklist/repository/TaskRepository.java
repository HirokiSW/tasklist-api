package com.hiroki.tasklist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.hiroki.tasklist.model.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByComplete(Boolean complete);
    List<Task> findAllByOrderByDueDateAsc();
    List<Task> findAllByOrderByCreatedAtAsc();
}
