package com.example.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(@RequestParam(value = "usernameAlreadyExists", required = false) Boolean usernameAlreadyExists, Model model) {
        if (usernameAlreadyExists != null && usernameAlreadyExists) {
            model.addAttribute("usernameAlreadyExists", true);
        }

        model.addAttribute("content", "login");
        return "main";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
