package com.klu.controller;

import com.klu.model.User;
import com.klu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password, HttpSession session, Model model) {
        User user = userService.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            session.setAttribute("user", user);
            session.setAttribute("email", email); 

            if (user.getRole().equals("manager")) {
                return "redirect:/manager/dashboard";
            } else if (user.getRole().equals("employee")) {
                return "redirect:/employee/dashboard";
            }
        }

        model.addAttribute("error", "Invalid email or password");
        return "login";
    }
}
