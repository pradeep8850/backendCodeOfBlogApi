package com.pradeep.blog.application.payloads;

import com.pradeep.blog.application.constants.user.UserConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import org.springframework.lang.Nullable;

import javax.validation.constraints.*;

@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    private Integer id;

    @NotBlank
    @NotEmpty
    @Size(min = UserConstants.USERNAME_MIN_LENGTH, message = UserConstants.USERNAME_MIN_LENGTH_MESSAGE, max = UserConstants.USERNAME_MAX_LENGTH)
    private String username;

    @NotEmpty
    @NotBlank
    @Size(min = UserConstants.FIRST_NAME_MIN_LENGTH, max = UserConstants.FIRST_NAME_MAX_LENGTH, message = UserConstants.FIRST_NAME_LENGTH_MESSAGE)
    private String firstName;

    @NotEmpty
    @NotBlank
    @Size(min = UserConstants.LAST_NAME_MIN_LENGTH, max = UserConstants.LAST_NAME_MAX_LENGTH, message = UserConstants.LAST_NAME_LENGTH_MESSAGE)
    private String lastName;


    @Nullable
    private Long contact;

    @Size(max = UserConstants.ABOUT_MAX_LENGTH, message = UserConstants.ABOUT_LENGTH_MESSAGE)
    private String about;

    @NotBlank
    @NotEmpty
    @Size(max = UserConstants.GENDER_MAX_LENGTH, message = UserConstants.GENDER_LENGTH_MESSAGE)
    private String gender;

    @NotEmpty
    @NotBlank
    @Size(min = UserConstants.MAIL_MIN_LENGTH, max = UserConstants.MAIL_MAX_LENGTH, message = UserConstants.MAIL_LENGTH_MESSAGE)
    @Email(message = "Email address is not valid.")
    private String mail;


    @NotBlank
    @NotEmpty
    @Size(min = UserConstants.PASSWORD_MIN_LENGTH, max = UserConstants.PASSWORD_MAX_LENGTH, message = UserConstants.PASSWORD_LENGTH_MESSAGE)
    private String password;


}
