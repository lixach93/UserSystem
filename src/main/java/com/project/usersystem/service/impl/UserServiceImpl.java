package com.project.usersystem.service.impl;

import com.project.usersystem.model.User;
import com.project.usersystem.repository.UserRepository;
import com.project.usersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(long id) {
        return null;
    }

    @Override
    public void create(User user) {

    }
}
