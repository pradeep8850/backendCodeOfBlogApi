package com.pradeep.blog.application.constants.user;

import lombok.experimental.FieldNameConstants;

public class UserConstants {


    public static final int USERNAME_MIN_LENGTH = 4;
    public static final int USERNAME_MAX_LENGTH = 20;
    public static final String USERNAME_MIN_LENGTH_MESSAGE = "Username should contain minimum 4 and maximum 20 characters.";

    public static final int FIRST_NAME_MIN_LENGTH = 4;
    public static final int FIRST_NAME_MAX_LENGTH = 15;
    public static final String FIRST_NAME_LENGTH_MESSAGE = "Firstname should contain minimum 4 and maximum 15 characters.";

    public static final int LAST_NAME_MIN_LENGTH = 3;
    public static final int LAST_NAME_MAX_LENGTH = 15;
    public static final String LAST_NAME_LENGTH_MESSAGE = "Lastname should contain minimum 3 and maximum 15 characters.";

    public static final int CONTACT_MAX_LENGTH = (int) 999999999999l;
    public static final long CONTACT_MIN_LENGTH = 9999999999l;
    public static final String CONTACT_LENGTH_MESSAGE = "Contact number should be correct.";

    public static final int ABOUT_MAX_LENGTH = 255;
    public static final String ABOUT_LENGTH_MESSAGE = "User information should not more then 255 characters.";

    public static final int GENDER_MAX_LENGTH = 6;
    public static final String GENDER_LENGTH_MESSAGE = "Gender is not correct. Please check and try again.";

    public static final int MAIL_MAX_LENGTH = 50;
    public static final int MAIL_MIN_LENGTH = 2;
    public static final String MAIL_LENGTH_MESSAGE = "Mail is not correct. Please check and try again.";

    public static final int PASSWORD_MAX_LENGTH = 60;
    public static final int PASSWORD_MIN_LENGTH = 4;
    public static final String PASSWORD_LENGTH_MESSAGE = "Password should contain min 4 and maximum 60 characters.";

    public static final String USERNAME_ALREADY_EXIST_MESSAGE = "User with this username is already exists. Please check your entries and try again.";
    public static final String USER_DELETED_MESSAGE = "User successfully deleted";
    public static final String DUPLICATE_USER_CONTACT = "User with this contact is already exists. Please check and provide correct contact.";

    public static final String NO_USER_FOUND_BY_FIRST_NAME = "No User found with given first name. Please try again with different first name.";
    public static final String NO_USER_FOUND_BY_LAST_NAME = "No User found with given last name. Please try again with different last name.";
    public static final String NO_USER_FOUND_BY_GENDER = "No user found with provided gender. Please select gender correctly.";
}
