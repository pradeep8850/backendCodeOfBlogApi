package com.pradeep.blog.application.controllers;

import com.pradeep.blog.application.constants.comment.CommentConstants;
import com.pradeep.blog.application.constants.helper.HelperResponse;
import com.pradeep.blog.application.exception.ApiResponse;
import com.pradeep.blog.application.payloads.CommentDto;
import com.pradeep.blog.application.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private HelperResponse helperResponse;

    @PostMapping("/post/{postId}/comment")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto,
                                                    @PathVariable(value = "postId") Integer postId) {
        CommentDto createdComment = this.commentService.createComment(postId, commentDto);
        return new ResponseEntity<CommentDto>(createdComment, HttpStatus.CREATED);
    }

    @GetMapping("/post/comment/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("commentId") Integer commentId){
        CommentDto comment = this.commentService.getCommentById(commentId);
        return new ResponseEntity<CommentDto>(comment, HttpStatus.OK);
    }


    @DeleteMapping("/post/{postId}/comment/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable ("postId") Integer postId, @PathVariable ("commentId") Integer commentId){
        this.commentService.deleteComment(postId, commentId);
        return helperResponse.getApiResponse(CommentConstants.COMMENT_DELETED, true, HttpStatus.OK);
    }

}
