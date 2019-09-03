package com.project.usersystem.controller;


import com.project.usersystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    @GetMapping
    @PreAuthorize(value = "hasRole('ANONYMOUS')")
    public String login(Model model) {
        return "login";
    }


    @PostMapping("/authentication")
    public String redirect(@RequestParam(value = "error", required = false) String error, Model model,
                           HttpServletRequest request) {

        if (error != null) {
            Exception exception = (Exception) request.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

            model.addAttribute("error", exception.getMessage());
            return "login";
        }
        return "redirect:/";

    }


}
