package com.project.usersystem.model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@TypeDef(
        name = "role_enum",
        typeClass = PostgreSQLEnumType.class
)
@TypeDef(
        name = "status_enum",
        typeClass = PostgreSQLEnumType.class
)
@NoArgsConstructor
@AllArgsConstructor
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 7278921055457709135L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String userName;
    private String password;
    private String firstName;
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Type(type = "role_enum")
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Type(type = "status_enum")
    private UserStatus status;

    private LocalDateTime date;


    public enum UserRole {

        ADMIN,
        USER;
    }

    public enum UserStatus {

        ACTIVE,
        INACTIVE;
    }
}
