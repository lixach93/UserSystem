package com.project.usersystem.service;

import com.project.usersystem.model.UserAccount;

import java.util.List;

public interface UserService {

    List<UserAccount> getAll();

    void update(UserAccount userAccount);

    UserAccount getById(long id);

    void create(UserAccount userAccount);

    void changeStatus(Long id);
}

