package com.project.usersystem.service;

import com.project.usersystem.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    Page<UserAccount> getAll(Pageable pageable);

    void update(UserAccount userAccount);

    UserAccount getById(long id);

    void create(UserAccount userAccount);

    void changeStatus(Long id);
}

