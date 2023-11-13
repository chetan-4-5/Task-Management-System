package com.klu.service;

import com.klu.model.Task;
import com.klu.model.User;
import com.klu.repository.TaskRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {

	private final TaskRepository taskRepository;

	@Autowired
	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void saveTask(Task task) {
		taskRepository.save(task);
	}

	@Override
	public void deleteTask(Long taskId) {
		taskRepository.deleteById(taskId);
	}

	@Override
	public List<Task> findTasksCreatedByManager(User manager) {

		return taskRepository.findByCreatedBy(manager);
	}

	

	@Override
	public Task findTaskById(Long taskId) {
		return taskRepository.findById(taskId).orElse(null);
	}

}
