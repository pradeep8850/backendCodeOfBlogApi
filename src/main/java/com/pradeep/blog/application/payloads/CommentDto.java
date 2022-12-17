package com.pradeep.blog.application.payloads;

import com.pradeep.blog.application.constants.post.PostConstants;
import com.pradeep.blog.application.constants.post.PostTableProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
public class CommentDto {

    private Integer commentId;

    @NotEmpty(message = PostConstants.CONTENT_NOT_EMPTY_MESSAGE)
    @NotBlank(message = PostConstants.CONTENT_NOT_BLANK_MESSAGE)
    @Size(max = PostTableProperty.POST_CONTENT_LENGTH, message = PostConstants.CONTENT_MESSAGE, min = PostConstants.MIN_CONTENT_LENGTH)
    private String content;

    //private PostDto post;
}
