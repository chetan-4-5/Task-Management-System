package com.klu.service;



import java.util.List;

import com.klu.model.Task;
import com.klu.model.User;




public interface TaskService {
    List<Task> findTasksCreatedByManager(User manager);
    void saveTask(Task task);
    void deleteTask(Long taskId);
    
    Task findTaskById(Long taskId);
}
