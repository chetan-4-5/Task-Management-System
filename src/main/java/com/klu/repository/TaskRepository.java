package com.klu.repository;

import com.klu.model.Task;
import com.klu.model.User;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
	List<Task> findByCreatedBy(User createdBy);
	void deleteById(Long taskId);
}
