package com.pradeep.blog.application.entities;

import com.pradeep.blog.application.constants.category.CategoryTableProperty;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = CategoryTableProperty.CATEGORY_ID)
    private Integer categoryId;

    @NotEmpty
    @NotNull
    @Column(name = CategoryTableProperty.CATEGORY_TITLE, unique = true, nullable = false, length = 100)
    private String categoryTitle;

    @NotNull
    @NotEmpty
    @Column(name = CategoryTableProperty.CATEGORY_DESCRIPTION, nullable = false)
    private String categoryDescription;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Post> posts = new ArrayList<Post>();

}
