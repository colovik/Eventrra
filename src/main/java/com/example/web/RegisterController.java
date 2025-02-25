package com.example.web;

import com.example.exceptions.InvalidArgumentsException;
import com.example.exceptions.UsernameAlreadyExistsException;
import com.example.model.Enumerations.Role;
import com.example.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class RegisterController {

    private final AuthService authService;

    public RegisterController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/register")
    public String getRegisterPage(@RequestParam(value = "usernameAlreadyExists", required = false) Boolean usernameAlreadyExists, Model model) {
        List<String> roles = Arrays.stream(Role.values())
                .map(Role::name)
                .filter(role -> !role.equals("ROLE_USER") && !role.equals("ROLE_ADMIN"))
                .collect(Collectors.toList());

        if (usernameAlreadyExists != null && usernameAlreadyExists) {
            model.addAttribute("usernameAlreadyExists", true);
        }

        model.addAttribute("roles", roles);
        model.addAttribute("content", "register");
        return "main";
    }

    @PostMapping("/register")
    @Transactional
    public String handleRegister(@RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String number,
                                 @RequestParam String password,
                                 @RequestParam String rpassword,
                                 @RequestParam Role role,
                                 HttpSession session,
                                 Model model) {
        session.setAttribute("username", username);
        session.setAttribute("name", name);
        session.setAttribute("password", password);
        session.setAttribute("rpassword", rpassword);
        session.setAttribute("number", number);
        session.setAttribute("role", role);

        try {
            switch (role.name().replace("ROLE_", "").toLowerCase()) {
                case "client":
                    authService.registerClient(name, username, number, password, rpassword, String.valueOf(Role.ROLE_CLIENT));
                    return "redirect:/login";

                case "band":
                    authService.registerBand(name, username, number, password, rpassword, String.valueOf(Role.ROLE_BAND), null);
                    return "redirect:/login";

                case "photographer":
                    authService.registerPhotographer(name, username, number, password, rpassword, String.valueOf(Role.ROLE_PHOTOGRAPHER), null, null);
                    return "redirect:/login";

                case "catering":
                    authService.registerCatering(name, username, number, password, rpassword, String.valueOf(Role.ROLE_CATERING), null, null);
                    return "redirect:/login";

                default:
                    return "redirect:/home?failedRegister=true";
            }
        } catch (UsernameAlreadyExistsException e) {
            return "redirect:/register?usernameAlreadyExists=true";
        } catch (InvalidArgumentsException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }

    @PostMapping("/finishRegisterBand")
    public void handleBandRegister(@RequestParam Integer price,
                                   HttpServletResponse response,
                                   HttpSession session) {
        String username = (String) session.getAttribute("username");
        String name = (String) session.getAttribute("name");
        String password = (String) session.getAttribute("password");
        String rpassword = (String) session.getAttribute("rpassword");
        String number = (String) session.getAttribute("number");

        try {
            authService.registerBand(name, username, number, password, rpassword, String.valueOf(Role.ROLE_BAND), price);
            response.sendRedirect("/login");
        } catch (UsernameAlreadyExistsException | IOException e) {
            try {
                response.sendRedirect("/register?usernameAlreadyExists=true");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @PostMapping("/finishRegisterPhotographer")
    public void handlePhotographerRegister(@RequestParam Integer price,
                                           @RequestParam(required = false) String portfolio,
                                           HttpServletResponse response,
                                           HttpSession session) {
        String username = (String) session.getAttribute("username");
        String name = (String) session.getAttribute("name");
        String password = (String) session.getAttribute("password");
        String rpassword = (String) session.getAttribute("rpassword");
        String number = (String) session.getAttribute("number");

        try {
            authService.registerPhotographer(name, username, number, password, rpassword, String.valueOf(Role.ROLE_PHOTOGRAPHER), price, portfolio);
            response.sendRedirect("/login");
        } catch (UsernameAlreadyExistsException | IOException e) {
            try {
                response.sendRedirect("/register?usernameAlreadyExists=true");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    @PostMapping("/finishRegisterCatering")
    public void handleCateringRegister(@RequestParam Integer price,
                                       @RequestParam String address,
                                       HttpServletResponse response,
                                       HttpSession session) {
        String username = (String) session.getAttribute("username");
        String name = (String) session.getAttribute("name");
        String password = (String) session.getAttribute("password");
        String rpassword = (String) session.getAttribute("rpassword");
        String number = (String) session.getAttribute("number");

        try {
            authService.registerCatering(name, username, number, password, rpassword, String.valueOf(Role.ROLE_CATERING), price, address);
            response.sendRedirect("/login");
        } catch (UsernameAlreadyExistsException | IOException e) {
            try {
                response.sendRedirect("/register?usernameAlreadyExists=true");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
