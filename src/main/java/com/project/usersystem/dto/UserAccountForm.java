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

    @Pattern(regexp = "[a-zA-Z]{3,16}", message = "Only latin. From 3 to 16 symbols")
    private String userName;

    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{3,16}$", message = "Latin symbols and digits. From 3 to 16. Min one digit, one symbols")
    private String password;

    @Pattern(regexp = "[a-zA-Z]{1,16}", message = "Only latin. From 1 to 16 symbols")
    private String firstName;

    @Pattern(regexp = "[a-zA-Z]{1,16}", message = "Only latin. From 1 to 16 symbols")
    private String lastName;

    private UserAccount.UserRole role;
    private UserAccount.UserStatus status;

    public  UserAccount toUserAccount(){

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
