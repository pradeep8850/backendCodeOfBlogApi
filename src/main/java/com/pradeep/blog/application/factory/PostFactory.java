package com.pradeep.blog.application.factory;

import com.pradeep.blog.application.entities.Post;
import com.pradeep.blog.application.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostFactory {
    @Autowired
    private PostRepository postRepository;

    public Post findSinglePostById(Integer postId) {
        Post singlePost = this.postRepository.findById(postId).orElseThrow(() -> ResourceNotFound.resourceNotFoundException("Post", "PostId", postId));
        return singlePost;
    }

}
