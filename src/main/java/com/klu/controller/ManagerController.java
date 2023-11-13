package com.klu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.klu.model.Task;
import com.klu.model.User;
import com.klu.service.TaskService;
import com.klu.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/manager")
public class ManagerController {

	private final UserService userService;
	private final TaskService taskService;

	public ManagerController(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}

	@GetMapping("/dashboard")
	public String managerDashboard(Model model, HttpSession session) {
		String managerEmail = (String) session.getAttribute("email");

		User manager = userService.findByEmail(managerEmail);

		// Fetch a list of employees from the database based on their role
		List<User> employees = userService.findUsersByRole("employee");

		List<Task> tasks = taskService.findTasksCreatedByManager(manager);

		model.addAttribute("manager", manager);
		model.addAttribute("employees", employees);
		model.addAttribute("tasks", tasks);

		return "manager/dashboard";
	}

	@GetMapping("/createTask")
	public String showCreateTask() {

		return "manager/createTask";
	}

	@PostMapping("/createTask")
	public String createTask(@RequestParam String taskTitle, @RequestParam String taskDescription, HttpSession session,
			Model model) {
		// Retrieve the manager from the session
		User createdBy = (User) session.getAttribute("user");

		// Create a new Task object and set its properties
		Task task = new Task();
		task.setTitle(taskTitle);
		task.setDescription(taskDescription);
		task.setCreatedBy(createdBy);

		taskService.saveTask(task);

		// Set the taskTitle attribute in the model
		model.addAttribute("taskTitle", taskTitle);

		return "redirect:/manager/dashboard";
	}

	@GetMapping("/assignTask")
	public String showAssignTask(@RequestParam("taskId") Long taskId, Model model) {
		Task task = taskService.findTaskById(taskId);

		if (task != null) {
			model.addAttribute("taskTitle", task.getTitle());
			// Add other necessary attributes to the model if needed
		} else {
			// Handle the case where the task is not found
		}

		return "manager/assignTask"; // Return the template for the modal
	}
	
	@PostMapping("/deleteEmployee/{employeeId}")
	public String deleteEmployee(@PathVariable Long employeeId) {
	    userService.deleteEmployee(employeeId);
	    return "redirect:/manager/dashboard";
	}

	
	@PostMapping("/deleteTask/{taskId}")
	public String deleteTask(@PathVariable Long taskId) {
	    taskService.deleteTask(taskId);
	    return "redirect:/manager/dashboard";
	}
	
	@PostMapping("/manager/assignTask/{taskId}")
	public String assignTask(@PathVariable Long taskId, @RequestParam Long employeeId) {
	    // Retrieve the task and employee from your service
	    Task task = taskService.findTaskById(taskId);
	    User employee = userService.findById(employeeId);

	    if (task != null && employee != null) {
	        task.setAssignedTo(employee);
	        taskService.saveTask(task);
	    }

	    return "redirect:/manager/dashboard";
	}



}
