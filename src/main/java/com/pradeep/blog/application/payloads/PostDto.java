package com.pradeep.blog.application.payloads;

import com.pradeep.blog.application.constants.post.PostConstants;
import com.pradeep.blog.application.constants.post.PostTableProperty;import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class PostDto {

    private Integer postId;

    @NotBlank(message = PostConstants.TITLE_NOT_BLANK_MESSAGE)
    @NotEmpty(message = PostConstants.TITLE_NOT_EMPTY_MESSAGE)
    @Size(min = PostConstants.MIN_TITLE_LENGTH, max = PostTableProperty.POST_TITLE_LENGTH, message = PostConstants.TITLE_MESSAGE)
    private String title;

    @NotEmpty(message = PostConstants.CONTENT_NOT_EMPTY_MESSAGE)
    @NotBlank(message = PostConstants.CONTENT_NOT_BLANK_MESSAGE)
    @Size(max = PostTableProperty.POST_CONTENT_LENGTH, message = PostConstants.CONTENT_MESSAGE, min = PostConstants.MIN_CONTENT_LENGTH)
    private String content;

    private Date creationDate;

    private UserDto user;

    private CategoryDto category;

}
