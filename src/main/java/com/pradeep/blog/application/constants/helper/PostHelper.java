package com.pradeep.blog.application.constants.helper;

import com.pradeep.blog.application.entities.Post;
import com.pradeep.blog.application.payloads.PostDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostHelper {

    @Autowired
    private ModelMapper modelMapper;

    public Post convertPostDtoToPost(PostDto postDto) {
        Post post = this.modelMapper.map(postDto, Post.class);
        return post;
    }

    public PostDto convertPostToPostDto(Post post) {
        PostDto postDto = this.modelMapper.map(post, PostDto.class);
        return postDto;
    }
}
