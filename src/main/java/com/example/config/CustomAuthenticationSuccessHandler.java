package com.example.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) {
        // Manually set the user in the session
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpRequest = attributes.getRequest();
//        httpRequest.getSession().setAttribute("user", authentication.getPrincipal());

        // Get the authenticated user (assuming authentication.getPrincipal() returns a User object)
        User user = (User) authentication.getPrincipal();

        // Set the user object in the session
        httpRequest.getSession().setAttribute("user", user);

        // Set the username in the session
        httpRequest.getSession().setAttribute("username", user.getUsername());
        // Redirect to the home page or user profile page after login
        try {
            response.sendRedirect("/home");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

