package com.pradeep.blog.application.services;

import com.pradeep.blog.application.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();

    CategoryDto getSingleCategory(Integer categoryId);

    CategoryDto createCategory(CategoryDto categoryDto);

    void deleteCategory(Integer categoryId);

    CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId);

    List<CategoryDto> searchCategoryByTitle(String title);

    List<CategoryDto> searchCategoryByDescription(String description);
}
