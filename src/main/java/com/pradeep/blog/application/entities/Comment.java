package com.pradeep.blog.application.entities;

import com.pradeep.blog.application.constants.comment.CommentTableProperty;
import com.pradeep.blog.application.constants.post.PostTableProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = CommentTableProperty.COMMENT_TABLE_NAME)
public class Comment {
    @Id
    @Column(name = CommentTableProperty.COMMENT_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(name = CommentTableProperty.CONTENT)
    private String content;

    @ManyToOne
    @JoinColumn(name = PostTableProperty.POST_ID)
    private Post post;

}
