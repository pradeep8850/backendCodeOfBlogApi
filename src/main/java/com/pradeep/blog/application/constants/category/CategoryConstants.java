package com.pradeep.blog.application.constants.category;

public class CategoryConstants {
    public static final int CATEGORY_TITLE_MIN_LENGTH=4;
    public static final int CATEGORY_TITLE_MAX_LENGTH=25;
    public static final String CATEGORY_TITLE_LENGTH_MESSAGE="Category title should be minimum 4 and maximum 25 characters.";

    public static final int CATEGORY_DESCRIPTION_MIN_LENGTH=5;
    public static final int CATEGORY_DESCRIPTION_MAX_LENGTH=132;
    public static final String CATEGORY_DESCRIPTION_LENGTH_MESSAGE="Category description should be minimum 5 and maximum 132 characters.";

    public static final String CATEGORY_ALREADY_EXISTS="Category with this name is already exists. Please provide unique category title.";
    public static final String CATEGORY_DELETE_MESSAGE="Category successfully deleted.";
}
