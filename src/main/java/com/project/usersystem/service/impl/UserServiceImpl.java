package com.project.usersystem.service.impl;

import com.project.usersystem.ResourceNotFoundException;
import com.project.usersystem.dto.UserAccountForm;
import com.project.usersystem.dto.UserAccountUpdateDTO;
import com.project.usersystem.model.UserAccount;
import com.project.usersystem.repository.UserRepository;
import com.project.usersystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<UserAccountUpdateDTO> getAll() {

        return userRepository.findAll()
                .stream()
                .map(UserAccountUpdateDTO::fromUserAccount)
                .collect(Collectors.toList());


    }

    @Override
    public void update(UserAccount userAccount) {
        findById(userAccount.getId());
        userRepository.save(userAccount);
    }

    @Override
    @Transactional(readOnly = true)
    public UserAccount getById(long id) {
        return findById(id);

    }

    @Override
    public void create(UserAccountForm dto) {
        UserAccount userAccount = dto.toUserAccount();
        userRepository.save(userAccount);
    }

    @Override
    public void changeStatus(Long id) {
        UserAccount userAccount = findById(id);
        UserAccount.UserStatus status = userAccount.getStatus();
        if(UserAccount.UserStatus.ACTIVE == status){
            userAccount.setStatus(UserAccount.UserStatus.INACTIVE);
        }else{
            userAccount.setStatus(UserAccount.UserStatus.ACTIVE);
        }
        userRepository.save(userAccount);
    }


    @Transactional(propagation = Propagation.MANDATORY)
    UserAccount findById(Long id) {
        Optional<UserAccount> optionalUserAccount = userRepository.findById(id);
        optionalUserAccount.orElseThrow(ResourceNotFoundException::new);
        return optionalUserAccount.get();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAccount user = userRepository.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid User");
        }

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        if(user.getStatus() == UserAccount.UserStatus.INACTIVE) {

            return   new  User(user.getUserName(),user.getPassword(),true,true,true,false,grantedAuthorities);

        }
        return new  User(user.getUserName(), user.getPassword(), grantedAuthorities);


    }
}
