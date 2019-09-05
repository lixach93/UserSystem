package com.project.usersystem.repository;

import com.project.usersystem.model.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserAccount, Long> {

    UserAccount findByUserName(String userName);
    Page<UserAccount> findAll(Pageable pageable);
}
