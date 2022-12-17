package com.pradeep.blog.application.implementation;

import com.pradeep.blog.application.constants.helper.CommentHelper;
import com.pradeep.blog.application.entities.Comment;
import com.pradeep.blog.application.entities.Post;
import com.pradeep.blog.application.factory.CommentFactory;
import com.pradeep.blog.application.factory.PostFactory;
import com.pradeep.blog.application.payloads.CommentDto;
import com.pradeep.blog.application.repositories.CommentRepository;
import com.pradeep.blog.application.services.CommentService;
import com.pradeep.blog.application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImplementation implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentHelper commentHelper;

    @Autowired
    private PostFactory postFactory;

    @Autowired
    private CommentFactory commentFactory;


    @Override
    public CommentDto createComment(Integer postId, CommentDto commentDto) {
        Post post = this.postFactory.findSinglePostById(postId);
        Comment comment = this.commentHelper.convertCommentDtoToComment(commentDto);
        comment.setPost(post);
        Comment savedComment = this.commentRepository.save(comment);
        return this.commentHelper.convertCommentToCommentDto(savedComment);
    }

    @Override
    public CommentDto getCommentById(Integer commentId) {
        return this.commentHelper.
                convertCommentToCommentDto(this.commentFactory.
                        getSingleCommentById(commentId));
    }


    @Override
    public void deleteComment(Integer postId, Integer commentId) {
        Post post = this.postFactory.findSinglePostById(postId);
        if (post != null) {
            this.commentRepository.deleteById(commentId);
        }
    }
}
