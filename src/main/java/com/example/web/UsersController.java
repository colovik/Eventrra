package com.example.web;

import com.example.model.Enumerations.Role;
import com.example.model.User;
import com.example.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UsersController {

    private final UserService userService;
    private final BandService bandService;
    private final CateringService cateringService;
    private final PhotographerService photographerService;

    public UsersController(UserService userService, BandService bandService,
                           CateringService cateringService,
                           PhotographerService photographerService) {
        this.userService = userService;
        this.bandService = bandService;
        this.cateringService = cateringService;
        this.photographerService = photographerService;
    }

    @GetMapping("/users")
    public String getUsers(@RequestParam(value = "roleFilter", required = false) String roleFilter,
                           Model model) {

        List<User> users;
        if (roleFilter != null && !roleFilter.isEmpty()) {
            users = userService.getUsersByRole(roleFilter);
        } else {
            users = userService.getAllUsers();
        }

        model.addAttribute("users", users);
        model.addAttribute("roles", Role.values());
        model.addAttribute("content", "users");
        model.addAttribute("flag", false);
        return "main";
    }

    @GetMapping("/bands")
    public String getBands(Model model) {
        model.addAttribute("users", this.bandService.findAll());
        model.addAttribute("content", "users");
        model.addAttribute("flag", false);

        return "main";
    }

    @GetMapping("/caterings")
    public String getCaterings(Model model) {
        model.addAttribute("users", this.cateringService.findAll());
        model.addAttribute("content", "users");
        model.addAttribute("flag", false);
        model.addAttribute("catering", true);
        return "main";
    }

    @GetMapping("/photographers")
    public String getPhotographers(Model model) {
        model.addAttribute("users", this.photographerService.findAll());
        model.addAttribute("content", "users");
        model.addAttribute("flag", false);
        return "main";
    }
}
