package com.pradeep.blog.application.constants.helper;

import com.pradeep.blog.application.entities.Comment;
import com.pradeep.blog.application.payloads.CommentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentHelper {

    @Autowired
    private ModelMapper modelMapper;

    public CommentDto convertCommentToCommentDto(Comment comment) {
        return this.modelMapper.map(comment, CommentDto.class);
    }

    public Comment convertCommentDtoToComment(CommentDto commentDto) {
        return this.modelMapper.map(commentDto, Comment.class);
    }

    ;

}
