package com.project.usersystem.dto;


import com.project.usersystem.model.UserAccount;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserAccountUpdateDTO {

    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";
    private String id;
    private String userName;
    private String password;
    @Pattern(regexp = "\\w{1,16}", message = "Only latin. From 1 to 16 symbols")
    private String firstName;
    @Pattern(regexp = "\\w{1,16}")
    private String lastName;
    private String role;
    private String status;
    private String createdDate;


    public static UserAccountUpdateDTO fromUserAccount(UserAccount userAccount) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        return UserAccountUpdateDTO.builder()
                .id(userAccount.getId().toString())
                .userName(userAccount.getUserName())
                .password(userAccount.getPassword())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .role(userAccount.getRole().name())
                .status(userAccount.getStatus().name())
                .createdDate(userAccount.getDate().format(formatter))
                .build();
    }

    public  UserAccount toUserAccount(Long id){
        UserAccount userAccount = setFields();
        userAccount.setId(id);
        return userAccount;
    }


    private UserAccount setFields(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);
        return  UserAccount.builder()
                .userName(userName)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .role(UserAccount.UserRole.valueOf(role))
                .status(UserAccount.UserStatus.valueOf(status))
                .date(LocalDateTime.parse(createdDate,formatter))
                .build();
    }



}