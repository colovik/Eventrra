package com.example.web;

import com.example.service.AuthService;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private final AuthService authService;
    private final UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public LoginController(AuthService authService, UserService userService) {
        this.authService = authService;
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        model.addAttribute("content", "login");
        return "main";
    }

//    @PostMapping("/login")
//    public String handleLogin(@RequestParam String username,
//                              @RequestParam String password,
//                              HttpServletRequest request,
//                              Model model) {
//        User user = null;
//        try {
//            user = authService.login(username, password);
//            request.getSession().setAttribute("user", user);
//            request.getSession().setAttribute("username",username);
//            request.getSession().setAttribute("role", user.getRole().name());
//            return "redirect:/home";
//        } catch (InvalidUserCredentialsException | InvalidUsernameOrPasswordException exception) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", exception.getMessage());
//            return "login";
//        }
//    }

//    @PostMapping("/login")
//    public String handleLogin(@RequestParam String username,
//                              @RequestParam String password,
//                              Model model,
//                              AuthenticationManager authenticationManager) {
//        try {
//            // Perform authentication via Spring Security (this happens automatically in the authentication filter)
//            Authentication authentication = authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(username, password)
//            );
//
//            // Store the authentication in the SecurityContext
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return "redirect:/home";
//        } catch (BadCredentialsException exception) {
//            model.addAttribute("hasError", true);
//            model.addAttribute("error", exception.getMessage());
//            return "login";
//        }
//    }


    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}
