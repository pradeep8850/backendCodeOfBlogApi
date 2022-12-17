package com.pradeep.blog.application.repositories;

import com.pradeep.blog.application.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer > {
}