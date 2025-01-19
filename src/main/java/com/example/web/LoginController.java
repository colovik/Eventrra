package com.example.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("content", "login");
        return "main";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
