package com.example.web;

import com.example.exceptions.InvalidUserCredentialsException;
import com.example.exceptions.InvalidUsernameOrPasswordException;
import com.example.model.User;
import com.example.service.AuthService;
import com.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final AuthService authService;
    private final UserService userService;

    public LoginController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@RequestParam String username,
                              @RequestParam String password,
                              HttpServletRequest request,
                              Model model) {
//                              HttpServletResponse resp) throws IOException {
        User user = null;
        try {
            user = authService.login(username, password);
            request.getSession().setAttribute("user", user);
            request.getSession().setAttribute("username",username);
            request.getSession().setAttribute("role",
                    userService.findByUsername(username).get().getRole().toString());
            System.out.println(request.getSession().getAttribute("role").toString());
            return "redirect:/home";
        } catch (InvalidUserCredentialsException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        } catch (InvalidUsernameOrPasswordException exception) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", exception.getMessage());
            return "login";
        }
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
