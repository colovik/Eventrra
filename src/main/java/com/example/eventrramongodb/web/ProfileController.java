package com.example.eventrramongodb.web;

import com.example.eventrramongodb.exceptions.UserNotFoundException;
import com.example.eventrramongodb.model.*;
import com.example.eventrramongodb.model.Enumerations.Role;
import com.example.eventrramongodb.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProfileController {
    private final UserService userService;
    private final ClientService clientService;
    private final BandService bandService;
    private final CateringService cateringService;
    private final PhotographerService photographerService;
    private final AdminService adminService;

    private final PasswordEncoder passwordEncoder;

    public ProfileController(UserService userService, ClientService clientService, BandService bandService, CateringService cateringService, PhotographerService photographerService, AdminService adminService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.clientService = clientService;
        this.bandService = bandService;
        this.cateringService = cateringService;
        this.photographerService = photographerService;
        this.adminService = adminService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/profile")
    public String getProfile(HttpServletRequest req, Model model) {
        String username = (String) req.getSession().getAttribute("username");
        User user = userService.findByUsername(username).get();

        model.addAttribute("user", user);
        model.addAttribute("content", "profile");

        return "main";
    }

    @GetMapping("/editProfile/{id}")
    public String editProfile(@PathVariable("id") String id, Model model) {
        User user = userService.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("content", "edit_profile");

        return "main";
    }

    @PostMapping("/editProfile/{id}")
    public String updateProfile(@PathVariable("id") String id,
                                @RequestParam String name,
                                @RequestParam String username,
                                @RequestParam String phoneNumber,
                                @RequestParam(required = false) String newPassword,
                                @RequestParam(required = false) String confirmNewPassword,
                                @RequestParam(required = false) Integer price,
                                @RequestParam(required = false) String address,
                                @RequestParam(required = false) String portfolio,
                                HttpServletRequest req) {

        User user = userService.findById(id);

        user.setName(name);
        user.setUsername(username);
        user.setPhoneNumber(phoneNumber);

        if (newPassword != null && !newPassword.isEmpty() && newPassword.equals(confirmNewPassword)) {
            user.setPassword(passwordEncoder.encode(newPassword));  // Set the new password
        }

        Role role = user.getRole();

        if (role.equals(Role.ROLE_BAND)) {
            if (price != null) {
                ((Band) user).setPrice(price);
            }
            bandService.save((Band) user);
        } else if (role.equals(Role.ROLE_CLIENT)) {
            clientService.save((Client) user);
        } else if (role.equals(Role.ROLE_CATERING)) {
            if (price != null) {
                ((Catering) user).setPrice(price);
            }
            if (address != null) {
                ((Catering) user).setAddress(address);
            }
            cateringService.save((Catering) user);
        } else if (role.equals(Role.ROLE_PHOTOGRAPHER)) {
            if (price != null) {
                ((Photographer) user).setPrice(price);
            }
            if (portfolio != null) {
                ((Photographer) user).setPortfolio(portfolio);
            }
            photographerService.save((Photographer) user);
        } else {
            adminService.save((Admin) user);
        }
        userService.save(user);

        req.getSession().setAttribute("username", username);

        return "redirect:/profile";
    }

    @PostMapping("/deleteProfile/{id}")
    public String deleteProfile(@PathVariable("id") String id, HttpServletRequest request) {
        User user = userService.findById(id);

        if (user != null) {
            Role role = user.getRole();

            if (role.equals(Role.ROLE_CLIENT)) {
                clientService.delete((Client) user);
            } else if (role.equals(Role.ROLE_BAND)) {
                bandService.delete((Band) user);
            } else if (role.equals(Role.ROLE_CATERING)) {
                cateringService.delete((Catering) user);
            } else if (role.equals(Role.ROLE_PHOTOGRAPHER)) {
                photographerService.delete((Photographer) user);
            } else if (role.equals(Role.ROLE_ADMIN)) {
                adminService.delete((Admin) user);
            }

            userService.delete(user);
        }

        request.getSession().invalidate();

        return "redirect:/login";
    }

}
