package com.project.usersystem.controller;


import com.project.usersystem.dto.UserAccountForm;
import com.project.usersystem.dto.UserAccountDTO;
import com.project.usersystem.model.UserAccount;
import com.project.usersystem.service.UserService;
import com.project.usersystem.service.exception.RegistrationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

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
    public String get(Model model) {

        List<UserAccountDTO> dto = userService.getAll()
                .stream()
                .map(UserAccountDTO::fromUserAccount)
                .collect(Collectors.toList());
        model.addAttribute("users", dto);

        return "list";
    }


    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id, Model model) {

        UserAccount userAccount = userService.getById(id);
        model.addAttribute("userAccount", UserAccountDTO.fromUserAccount(userAccount));
        return "view";
    }

    @PostMapping("/{id}")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String changeStatus(@PathVariable("id") Long id) {

        userService.changeStatus(id);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String showForm(@PathVariable("id") Long id, Model model) {

        UserAccount userAccount = userService.getById(id);
        model.addAttribute("userAccount", UserAccountDTO.fromUserAccount(userAccount));
        model.addAttribute("id", userAccount.getId());
        model.addAttribute("roleTypes", UserAccount.UserRole.values());
        model.addAttribute("statusTypes", UserAccount.UserStatus.values());
        return "edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String edit(@PathVariable("id") Long id, @Valid @ModelAttribute("userAccount") UserAccountDTO userAccountDTO, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("roleTypes", UserAccount.UserRole.values());
            model.addAttribute("statusTypes", UserAccount.UserStatus.values());
            return "edit";
        }
        UserAccount userAccount = userAccountDTO.toUserAccount(id);
        userService.update(userAccount);

        return "redirect:/user";
    }

    @GetMapping("/new")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String registration(Model model) {

        model.addAttribute("userForm", new UserAccountForm());
        model.addAttribute("roleTypes", UserAccount.UserRole.values());
        model.addAttribute("statusTypes", UserAccount.UserStatus.values());
        return "createUser";
    }

    @PostMapping("/new")
    @PreAuthorize(value = "hasRole('ADMIN')")
    public String registration(@Valid @ModelAttribute("userForm") UserAccountForm userDto, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            model.addAttribute("roleTypes", UserAccount.UserRole.values());
            model.addAttribute("statusTypes", UserAccount.UserStatus.values());
            return "createUser";
        }
        try {
            UserAccount userAccount = userDto.toUserAccount();
            userAccount.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userService.create(userAccount);

        } catch (RegistrationException e) {
            model.addAttribute("error", " error");
            return "registration";
        }

        return "redirect:/user";
    }

}
