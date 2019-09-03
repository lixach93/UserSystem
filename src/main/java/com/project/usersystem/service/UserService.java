package com.project.usersystem.service;

import com.project.usersystem.dto.UserAccountForm;
import com.project.usersystem.dto.UserAccountUpdateDTO;
import com.project.usersystem.model.UserAccount;

import java.util.List;

public interface UserService {

    List<UserAccountUpdateDTO> getAll();

    void update(UserAccount userAccount);

    UserAccount getById(long id);

    void create(UserAccountForm dto);

    void changeStatus(Long id);
}

