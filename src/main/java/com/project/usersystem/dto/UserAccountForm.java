package com.project.usersystem.dto;


import com.project.usersystem.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountForm {

    private String userName;
    private String password;
    private String firstName;
    private String lastName;
    private UserAccount.UserRole role;
    private UserAccount.UserStatus status;

    public UserAccount toUserAccount(){

        UserAccount userAccount = setFields();
        userAccount.setDate(LocalDateTime.now());
        return userAccount;
    }


    private UserAccount setFields(){
        return  UserAccount.builder()
                .userName(userName)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .role(role)
                .status(status)
                .build();
    }

}
