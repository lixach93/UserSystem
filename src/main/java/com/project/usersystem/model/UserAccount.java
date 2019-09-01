package com.project.usersystem.model;

import com.vladmihalcea.hibernate.type.basic.PostgreSQLEnumType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
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
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "\\w{3,16}")
    @Column(unique = true)
    private String userName;
    private String password;


    @Pattern(regexp = "\\w{1,16}")
    private String firstName;

    @Pattern(regexp = "\\w{1,16}")
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Type(type = "role_enum")
    private UserRole role;

    @Enumerated(EnumType.STRING)
    @Type(type = "status_enum")
    private UserStatus status;

    @Column(nullable = false)
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
