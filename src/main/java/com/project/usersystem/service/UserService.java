package com.project.usersystem.service;

import com.project.usersystem.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(long id);

    void create(User user);
}
