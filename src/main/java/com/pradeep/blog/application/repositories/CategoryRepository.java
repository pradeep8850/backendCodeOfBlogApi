package com.pradeep.blog.application.repositories;

import com.pradeep.blog.application.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByCategoryTitle(String categoryTitle);

    List<Category> findByCategoryTitleContaining(String title);

    List<Category> findByCategoryDescriptionContaining(String description);

}
