package com.pradeep.blog.application.entities;

import com.pradeep.blog.application.constants.category.CategoryTableProperty;
import com.pradeep.blog.application.constants.post.PostTableProperty;
import com.pradeep.blog.application.constants.user.UserTableProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = PostTableProperty.POST_TABLE_NAME)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = PostTableProperty.POST_ID)
    private Integer postId;

    @Column(name = PostTableProperty.POST_TITLE, length = PostTableProperty.POST_TITLE_LENGTH, nullable = false)
    private String title;

    @Column(name = PostTableProperty.POST_CONTENT, nullable = false, length = PostTableProperty.POST_CONTENT_LENGTH)
    private String content;

    private Date creationDate;

    @ManyToOne
    @JoinColumn(name = CategoryTableProperty.CATEGORY_ID)


    private Category category;

    @ManyToOne
    @JoinColumn(name = UserTableProperty.COLUMN_NAME_USERID)
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new LinkedList<>();

}
