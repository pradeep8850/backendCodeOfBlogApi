package com.pradeep.blog.application.factory;

import com.pradeep.blog.application.entities.Comment;
import com.pradeep.blog.application.payloads.CommentDto;
import com.pradeep.blog.application.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentFactory {
    @Autowired
    private CommentRepository commentRepository;

    public Comment getSingleCommentById(Integer commentId) {
        Comment comment = this.commentRepository.findById(commentId).orElseThrow(() -> ResourceNotFound.resourceNotFoundException("Post", "PostId", commentId));
        return comment;
    }
}
