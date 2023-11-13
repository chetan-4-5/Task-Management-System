package com.klu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.klu.model.User;
import com.klu.service.TaskService;
import com.klu.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

	private final UserService userService;
	private final TaskService taskService;

	public EmployeeController(UserService userService, TaskService taskService) {
		this.userService = userService;
		this.taskService = taskService;
	}

	@GetMapping("/dashboard")
	public String employeeDashboard(Model model, HttpSession session) {
		String employeeEmail = (String) session.getAttribute("email");

		User employee = userService.findByEmail(employeeEmail);

		model.addAttribute("employee", employee); 

		return "employee/dashboard"; 
	}
}
