package com.project.usersystem.service.impl;

import com.project.usersystem.ResourceNotFoundException;
import com.project.usersystem.model.UserAccount;
import com.project.usersystem.repository.UserRepository;
import com.project.usersystem.service.UserService;
import com.project.usersystem.service.exception.RegistrationException;
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

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserAccount> getAll() {

        return userRepository.findAll();
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
    public void create(UserAccount userAccount) {
        UserAccount userAccount2 = userRepository.findByUserName(userAccount.getUserName());
        if (userAccount2 != null) {
            throw new RegistrationException();
        }
        userRepository.save(userAccount);
    }

    @Override
    public void changeStatus(Long id) {
        UserAccount userAccount = findById(id);
        UserAccount.UserStatus status = userAccount.getStatus();
        if (UserAccount.UserStatus.ACTIVE == status) {
            userAccount.setStatus(UserAccount.UserStatus.INACTIVE);
        } else {
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

        if (user.getStatus() == UserAccount.UserStatus.INACTIVE) {

            return new User(user.getUserName(), user.getPassword(), true, true, true, false, grantedAuthorities);

        }
        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);


    }
}
