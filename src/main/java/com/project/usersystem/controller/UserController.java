package com.project.usersystem.controller;


import com.project.usersystem.dto.UserAccountForm;
import com.project.usersystem.dto.UserAccountUpdateDTO;
import com.project.usersystem.model.UserAccount;
import com.project.usersystem.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @GetMapping
    public String users(Model model) {
        model.addAttribute("users", userService.getAll());
        return "users";
    }

    @GetMapping("/new")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String registration(Model model) {

        model.addAttribute("userForm", new UserAccountForm());
        model.addAttribute("roleTypes", UserAccount.UserRole.values());
        model.addAttribute("statusTypes", UserAccount.UserStatus.values());
        return "createUser";
    }

    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model) {

        UserAccount userAccount = userService.getById(id);
        model.addAttribute("userAccount", UserAccountUpdateDTO.fromUserAccount(userAccount));
        return "view";
    }

    @PostMapping("/{id}")
    public String changeStatus(@PathVariable("id") Long id) {

        userService.changeStatus(id);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String showForm(@PathVariable("id") Long id, Model model) {

        UserAccount userAccount = userService.getById(id);
        model.addAttribute("userAccount", UserAccountUpdateDTO.fromUserAccount(userAccount));
        model.addAttribute("id", userAccount.getId());
        model.addAttribute("roleTypes", UserAccount.UserRole.values());
        model.addAttribute("statusTypes", UserAccount.UserStatus.values());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("userAccount") UserAccountUpdateDTO userAccountUpdateDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roleTypes", UserAccount.UserRole.values());
            model.addAttribute("statusTypes", UserAccount.UserStatus.values());
            return "edit";
        }
        UserAccount userAccount = userAccountUpdateDTO.toUserAccount(id);
        userService.update(userAccount);

        return "redirect:/user";
    }


    @PostMapping("/new")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String registration(@Valid @ModelAttribute("userDto") UserAccountForm userDto, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return "createUser";
        }
//        try {
          userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userService.create(userDto);
//        } catch (RegistrationException e) {
//            model.addAttribute("error"," error");
//            return "registration";
//        }

        return "redirect:/user";
    }

}
