package com.project.usersystem.dto;


import com.project.usersystem.model.UserAccount;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Builder
public class UserAccountOutput {

    private static final String DATE_FORMATTER = "yyyy-MM-dd HH:mm:ss";

    private String id;
    private String userName;
    private String firstName;
    private String lastName;
    private String role;
    private String status;
    private String createdDate;

    public static UserAccountOutput fromUserAccount(UserAccount userAccount) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMATTER);

        return UserAccountOutput.builder()
                .id(userAccount.getId().toString())
                .userName(userAccount.getUserName())
                .firstName(userAccount.getFirstName())
                .lastName(userAccount.getLastName())
                .role(userAccount.getRole().name())
                .status(userAccount.getStatus().name())
                .createdDate(userAccount.getDate().format(formatter))
                .build();
    }

}
