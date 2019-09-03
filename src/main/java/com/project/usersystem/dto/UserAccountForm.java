package com.project.usersystem.dto;


import com.project.usersystem.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountForm {


    private String userName;
    private String password;

    @Pattern(regexp = "\\w{1,16}")
    private String firstName;

    @Pattern(regexp = "\\w{1,16}")
    private String lastName;
    private UserAccount.UserRole role;
    private UserAccount.UserStatus status;

    public  UserAccount toUserAccount(){

        UserAccount userAccount = setFields();
        userAccount.setDate(LocalDateTime.now());

        return userAccount;
    }

    public UserAccount toUserAccount(Long id, LocalDateTime date , String password){

        UserAccount userAccount = setFields();
        userAccount.setId(id);
        userAccount.setPassword(password);
        userAccount.setDate(date);

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
