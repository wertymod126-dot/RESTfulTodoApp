package com.advertismnt.restfultodoapp.repository;

import com.advertismnt.restfultodoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByCompleted(boolean completed);
}
