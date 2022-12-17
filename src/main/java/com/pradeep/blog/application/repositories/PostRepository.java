package com.pradeep.blog.application.repositories;

import com.pradeep.blog.application.entities.Category;
import com.pradeep.blog.application.entities.Post;
import com.pradeep.blog.application.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);

    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

    List<Post> findByContentContaining(String content);

}
