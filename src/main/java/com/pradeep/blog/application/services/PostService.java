package com.pradeep.blog.application.services;

import com.pradeep.blog.application.payloads.PostDto;
import com.pradeep.blog.application.payloads.PostResponse;

import java.util.List;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);

    //  List<PostDto> getAllPosts();

    PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);

    void deleteSinglePost(Integer postId);

    PostDto getPost(Integer postId);

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> getPostsByCategory(Integer categoryId);

    List<PostDto> searchByTitle(String title);

    List<PostDto> searchByContent(String content);

}
