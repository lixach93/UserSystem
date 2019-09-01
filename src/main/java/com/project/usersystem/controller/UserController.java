package com.project.usersystem.controller;


import com.project.usersystem.dto.UserAccountForm;
import com.project.usersystem.dto.UserAccountOutput;
import com.project.usersystem.model.UserAccount;
import com.project.usersystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String users(Model model){
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/new")
    public String registration(Model model) {

        model.addAttribute("userForm", new UserAccountForm());
        model.addAttribute("roleTypes", UserAccount.UserRole.values());
        model.addAttribute("statusTypes", UserAccount.UserStatus.values());
        return "createUser";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model){

        UserAccountOutput userAccountOutput = userService.getById(id);
        model.addAttribute("userAccount", userAccountOutput);
        return "view";
    }

    @GetMapping("/{id}/edit")
    public String showForm(@PathVariable("id") Long id, Model model){

        UserAccountOutput userAccountOutput = userService.getById(id);
        model.addAttribute("userForm", userAccountOutput);
        model.addAttribute("roleTypes", UserAccount.UserRole.values());
        model.addAttribute("statusTypes", UserAccount.UserStatus.values());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model){

        UserAccountOutput userAccountOutput = userService.getById(id);
        model.addAttribute("userForm", userAccountOutput);
        return "edit";
    }


    @PostMapping("/new")
    public String registration(@Valid @ModelAttribute("userDto") UserAccountForm userDto, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return "createUser";
        }
//        try {
//           user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
         userService.create(userDto);
//        } catch (RegistrationException e) {
//            model.addAttribute("error"," error");
//            return "registration";
//        }

        return "redirect:/user";
    }

}
