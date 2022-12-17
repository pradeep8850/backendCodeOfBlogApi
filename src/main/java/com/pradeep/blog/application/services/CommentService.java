package com.pradeep.blog.application.services;

import com.pradeep.blog.application.payloads.CommentDto;

public interface CommentService {
    CommentDto createComment(Integer postId, CommentDto commentDto);

    CommentDto getCommentById(Integer commentId);

    void deleteComment(Integer postId, Integer commentId);

}