package com.example.web;

import com.example.exceptions.InvalidArgumentsException;
import com.example.exceptions.NoSuchUsernameException;
import com.example.model.Catering;
import com.example.model.Enumerations.Role;
import com.example.repository.CateringRepository;
import com.example.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Controller
public class RegisterController {

    private final AuthService authService;

    private final CateringRepository cateringRepository;


    public RegisterController(AuthService authService, CateringRepository cateringRepository) {
        this.authService = authService;
        this.cateringRepository = cateringRepository;
    }

    @GetMapping("/register")
    public String getRegistgerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String handleRegister(@RequestParam String name,
                                 @RequestParam String username,
                                 @RequestParam String number,
                                 @RequestParam String password,
                                 @RequestParam String rpassword,
                                 @RequestParam String role,
                                 HttpSession session) {
        session.setAttribute("username", username);
        session.setAttribute("name", name);
        session.setAttribute("password", password);
        session.setAttribute("rpassword", rpassword);
        session.setAttribute("number", number);
        session.setAttribute("role", role);
        if (Objects.equals(role, "client")) {
            authService.registerClient(name, username, number, password, rpassword, String.valueOf(Role.ROLE_CLIENT));
            try {
                return "redirect:/login";
            } catch (InvalidArgumentsException exception) {
                return "redirect:/register?error=" + exception.getMessage();

            }
        } else if (Objects.equals(role, "band")) {
            try {
                return "registerBand";
            } catch (InvalidArgumentsException exception) {
                return "redirect:/register?error=" + exception.getMessage();
            }
        } else if (Objects.equals(role, "photographer")) {
            try {
                return "registerPhotographer";
            } catch (InvalidArgumentsException exception) {
                return "redirect:/register?error=" + exception.getMessage();
            }
        }
        if (Objects.equals(role, "waiter")) {
            try {
                return "redirect:/registerWaiter";
            } catch (InvalidArgumentsException exception) {
                return "redirect:/register?error=" + exception.getMessage();
            }
        }
        if (Objects.equals(role, "catering")) {
            try {
                return "registerCatering";
            } catch (InvalidArgumentsException exception) {
                return "redirect:/register?error=" + exception.getMessage();
            }
        }
        return "register";
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
        authService.registerBand(name, username, number, password, rpassword, String.valueOf(Role.ROLE_BAND), price);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        authService.registerPhotographer(name, username, number, password, rpassword, String.valueOf(Role.ROLE_PHOTOGRAPHER), price, portfolio);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/registerWaiter")
    public String getSelectPage(Model model) {
        model.addAttribute("caterings", cateringRepository.findAll());
        return "registerWaiter";
    }

    @PostMapping("/finishRegisterWaiter")
    public void handleWaiterRegister(@RequestParam Integer free_day,
                                     @RequestParam Integer experience,
                                     @RequestParam String catering_name,
                                     HttpServletResponse response,
                                     HttpSession session) {
        String username = (String) session.getAttribute("username");
        String name = (String) session.getAttribute("name");
        String password = (String) session.getAttribute("password");
        String rpassword = (String) session.getAttribute("rpassword");
        String number = (String) session.getAttribute("number");
        Catering catering = cateringRepository.findAllByName(catering_name).stream().findFirst()
                .orElseThrow(NoSuchUsernameException::new);
        authService.registerWaiter(name, username, number, password, rpassword, String.valueOf(Role.ROLE_WAITER), free_day, experience, catering);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
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
        authService.registerCatering(name, username, number, password, rpassword, String.valueOf(Role.ROLE_CATERING), price, address);
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}