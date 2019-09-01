package com.project.usersystem.service.impl;

import com.project.usersystem.ResourceNotFoundException;
import com.project.usersystem.dto.UserAccountForm;
import com.project.usersystem.dto.UserAccountOutput;
import com.project.usersystem.model.UserAccount;
import com.project.usersystem.repository.UserRepository;
import com.project.usersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserAccountOutput> getAll() {

       return userRepository.findAll()
                .stream()
                .map(UserAccountOutput::fromUserAccount)
                .collect(Collectors.toList());


    }

    @Override
    @Transactional(readOnly = true)
    public UserAccountOutput getById(long id) {

        UserAccount userAccount = findById(id);
        return UserAccountOutput.fromUserAccount(userAccount);

    }

    @Override
    public void create(UserAccountForm dto) {
        UserAccount userAccount = dto.toUserAccount();
        userRepository.save(userAccount);
    }



    @Transactional(propagation = Propagation.MANDATORY)
    UserAccount findById(Long id){
        Optional<UserAccount> optionalUserAccount = userRepository.findById(id);
        optionalUserAccount.orElseThrow(ResourceNotFoundException::new);
        return optionalUserAccount.get();
    }

}
