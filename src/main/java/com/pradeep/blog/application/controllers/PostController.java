package com.pradeep.blog.application.controllers;

import com.pradeep.blog.application.constants.post.PostConstants;
import com.pradeep.blog.application.exception.ApiResponse;
import com.pradeep.blog.application.constants.helper.HelperResponse;
import com.pradeep.blog.application.payloads.PostDto;
import com.pradeep.blog.application.payloads.PostResponse;
import com.pradeep.blog.application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blog/api/")
public class PostController {
    @Autowired
    private PostService postService;

    @Autowired
    private HelperResponse helperResponse;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    private ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto,
                                               @PathVariable(value = "userId") Integer userId,
                                               @PathVariable(value = "categoryId") Integer categoryId) {
        PostDto createdPostDto = this.postService.createPost(postDto, userId, categoryId);
        return new ResponseEntity<PostDto>(createdPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/posts")
    public ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber",
            defaultValue = "0",
            required = false) Integer pageNumber,
                                                    @RequestParam(value = "pageSize",
                                                            defaultValue = "10",
                                                            required = false) Integer pageSize,
                                                    @RequestParam(value = "sortBy",
                                                            defaultValue = "postId",
                                                            required = false) String sortBy,
                                                    @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {


        PostResponse postResponse = this.postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<PostResponse>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    private ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId) {
        this.postService.deleteSinglePost(postId);
        return helperResponse.getApiResponse(PostConstants.USER_DELETED, true, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    private ResponseEntity<PostDto> getSinglePostById(@PathVariable("postId") Integer postId) {
        PostDto post = this.postService.getPost(postId);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/posts/user/{userId}")
    private ResponseEntity<List<PostDto>> findPostsByUser(@PathVariable("userId") Integer userID) {
        List<PostDto> postDtos = this.postService.getPostsByUser(userID);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/posts/category/{categoryId}")
    private ResponseEntity<List<PostDto>> findPostByCategory(@PathVariable("categoryId") Integer categoryId) {
        List<PostDto> postDtos = this.postService.getPostsByCategory(categoryId);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/posts/search/title/{title}")
    private ResponseEntity<List<PostDto>> searchPostByTitleContaining(@PathVariable("title") String title) {
        List<PostDto> postDtos = this.postService.searchByTitle(title);
        return ResponseEntity.ok(postDtos);
    }

    @GetMapping("/posts/search/content/{content}")
    private ResponseEntity<List<PostDto>> searchPostByContentContaining(@PathVariable("content") String content) {
        List<PostDto> postDtos = this.postService.searchByContent(content);
        return ResponseEntity.ok(postDtos);
    }

}
