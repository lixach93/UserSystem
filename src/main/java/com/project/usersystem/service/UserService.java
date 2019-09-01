package com.project.usersystem.service;

import com.project.usersystem.dto.UserAccountForm;
import com.project.usersystem.dto.UserAccountOutput;
import com.project.usersystem.model.UserAccount;

import java.util.List;

public interface UserService {

    List<UserAccountOutput> getAll();

    UserAccountOutput getById(long id);

    void create(UserAccountForm dto);

}
