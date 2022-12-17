package com.pradeep.blog.application.implementation;

import com.pradeep.blog.application.constants.common.CommonConstants;
import com.pradeep.blog.application.constants.helper.PostHelper;
import com.pradeep.blog.application.entities.Category;
import com.pradeep.blog.application.entities.Post;
import com.pradeep.blog.application.entities.User;
import com.pradeep.blog.application.exception.ResourceNotFoundException;
import com.pradeep.blog.application.payloads.PostDto;
import com.pradeep.blog.application.payloads.PostResponse;
import com.pradeep.blog.application.repositories.CategoryRepository;
import com.pradeep.blog.application.repositories.PostRepository;
import com.pradeep.blog.application.repositories.UserRepository;
import com.pradeep.blog.application.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImplementation implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private PostHelper postHelper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private PostResponse postResponse;

    private ResourceNotFoundException resourceNotFoundException(String name, String id, Integer resourceId) {
        return new ResourceNotFoundException(name, id, resourceId);
    }

    private List<PostDto> getConvertablePostDtos(List<Post> posts) {
        return posts.stream()
                .map((singlePost) -> this.postHelper.convertPostToPostDto(singlePost))
                .collect(Collectors.toList());
    }

    private User findUserById(Integer userId) {
        User user = this.userRepository.findById(userId).orElseThrow(() -> this.resourceNotFoundException("user", "user Id", userId));
        return user;
    }

    private Category findCategoryById(Integer categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> this.resourceNotFoundException("Category", "Category id", categoryId));
        return category;
    }

    private Post findSinglePostById(Integer postId) {
        Post singlePost = this.postRepository.findById(postId).orElseThrow(() -> this.resourceNotFoundException("Post", "PostId", postId));
        return singlePost;
    }


    @Override
    public PostDto createPost(PostDto postDto, Integer userId, Integer categoryId) {
        Category category = this.findCategoryById(categoryId);
        User user = this.findUserById(userId);
        Post post = this.postHelper.convertPostDtoToPost(postDto);
        post.setUser(user);
        post.setCategory(category);
        post.setCreationDate(new Date());
        Post savedPost = this.postRepository.save(post);
        return this.postHelper.convertPostToPostDto(savedPost);
    }

    private Pageable getPageable(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
        Sort sort = null;

        if (sortDir.equalsIgnoreCase("asc")) {
            sort = Sort.by(sortBy).ascending();
        } else if (sortDir.equalsIgnoreCase("des")) {
            sort = Sort.by(sortBy).descending();
        }
        Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
        return pageable;
    }

    @Override
    public PostResponse getAllPosts(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {

        Page<Post> pagePost = this.postRepository.findAll(getPageable(pageNumber, pageSize, sortBy, sortDir));

        if (pagePost.getSize() == 0) {
            throw new NullPointerException(CommonConstants.NO_POST_FOUND);
        }
        System.out.println(pagePost);
        List<Post> allPosts = pagePost.getContent();
        List<PostDto> postDtos = allPosts.stream()
                .map((singlePost) -> this.postHelper.convertPostToPostDto(singlePost))
                .collect(Collectors.toList());
        this.postResponse.setContent(postDtos);
        this.postResponse.setPageNumber(pagePost.getNumber());
        this.postResponse.setPageSize(pagePost.getSize());
        this.postResponse.setTotalElements(pagePost.getTotalElements());
        this.postResponse.setTotalPages(pagePost.getTotalPages());
        this.postResponse.setLastPage(pagePost.isLast());
        return this.postResponse;
    }

    @Override
    public void deleteSinglePost(Integer postId) {
        Post post = this.findSinglePostById(postId);
        this.postRepository.deleteById(post.getPostId());
    }

    @Override
    public PostDto getPost(Integer postId) {
        Post post = this.findSinglePostById(postId);
        return this.postHelper.convertPostToPostDto(post);
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        User user = this.findUserById(userId);
        List<Post> posts = this.postRepository.findByUser(user);
        return this.getConvertablePostDtos(posts);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {
        Category category = this.findCategoryById(categoryId);
        List<Post> posts = this.postRepository.findByCategory(category);

        return this.getConvertablePostDtos(posts);
    }


    @Override
    public List<PostDto> searchByTitle(String title) {
        List<Post> posts = this.postRepository.findByTitleContaining(title);
        if (posts.size() == 0) {
            throw new NullPointerException(CommonConstants.NO_POST_FOUND);
        }
        return this.getConvertablePostDtos(posts);

    }

    @Override
    public List<PostDto> searchByContent(String content) {
        List<Post> posts = this.postRepository.findByContentContaining(content);
        if (posts.size() == 0) {
            throw new NullPointerException(CommonConstants.NO_POST_FOUND);
        }
        return this.getConvertablePostDtos(posts);
    }
}
