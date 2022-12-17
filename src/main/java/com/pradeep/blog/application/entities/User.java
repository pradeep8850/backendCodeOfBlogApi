package com.pradeep.blog.application.entities;

import com.pradeep.blog.application.constants.user.UserConstants;
import com.pradeep.blog.application.constants.user.UserTableProperty;
import lombok.*;
import lombok.experimental.FieldNameConstants;

import javax.persistence.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = UserTableProperty.COLUMN_NAME_USERID)
    private Integer id;

    @Column(name = UserTableProperty.COLUMN_NAME_USERNAME, unique = true, nullable = false, length = UserConstants.USERNAME_MAX_LENGTH)
    private String username;

    @Column(name = UserTableProperty.COLUMN_NAME_FIRSTNAME, length = UserConstants.FIRST_NAME_MAX_LENGTH, nullable = false)
    private String firstName;

    @Column(name = UserTableProperty.COLUMN_NAME_LASTNAME, length = UserConstants.LAST_NAME_MAX_LENGTH, nullable = false)
    private String lastName;

    @Column(name = UserTableProperty.COLUMN_NAME_CONTACT, nullable = true)
    private Long contact;

    @Column(name = UserTableProperty.COLUMN_NAME_ABOUT, length = UserConstants.ABOUT_MAX_LENGTH, nullable = true)
    private String about;

    @Column(name = UserTableProperty.COLUMN_NAME_GENDER, length = UserConstants.GENDER_MAX_LENGTH, nullable = false)
    private String gender;

    @Column(name = UserTableProperty.COLUMN_NAME_MAIL, unique = true, length = UserConstants.MAIL_MAX_LENGTH, nullable = false)
    private String mail;

    @Column(name = UserTableProperty.COLUMN_NAME_PASSWORD, nullable = false, length = UserConstants.PASSWORD_MAX_LENGTH)
    private String password;


}
