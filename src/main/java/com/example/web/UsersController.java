package com.example.web;

import com.example.model.Enumerations.Role;
import com.example.model.User;
import com.example.service.BandService;
import com.example.service.CateringService;
import com.example.service.PhotographerService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
            users = userService.getActiveUsersByRole(roleFilter);
        } else {
            users = userService.findAllActiveUsers();
        }

        List<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .filter(role -> !role.equals("ROLE_USER") && !role.equals("ROLE_ADMIN"))
                .collect(Collectors.toList());

        model.addAttribute("users", users);
        model.addAttribute("roles", roles);
        model.addAttribute("content", "users");
        model.addAttribute("flag", false);
        return "main";
    }

    @GetMapping("/bands")
    public String getBands(Model model) {
        model.addAttribute("users", this.bandService.findAllActiveBands());
        model.addAttribute("content", "users");
        model.addAttribute("flag", false);

        return "main";
    }

    @GetMapping("/caterings")
    public String getCaterings(Model model) {
        model.addAttribute("users", this.cateringService.findAllActiveCaterings());
        model.addAttribute("content", "users");
        model.addAttribute("flag", false);
        model.addAttribute("catering", true);
        return "main";
    }

    @GetMapping("/photographers")
    public String getPhotographers(Model model) {
        model.addAttribute("users", this.photographerService.findAllActivePhotographers());
        model.addAttribute("content", "users");
        model.addAttribute("flag", false);
        return "main";
    }

}
