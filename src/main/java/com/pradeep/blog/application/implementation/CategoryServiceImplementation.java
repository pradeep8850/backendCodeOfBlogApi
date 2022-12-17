package com.pradeep.blog.application.implementation;

import com.pradeep.blog.application.constants.category.CategoryConstants;
import com.pradeep.blog.application.constants.common.CommonConstants;
import com.pradeep.blog.application.constants.helper.CategoryHelper;
import com.pradeep.blog.application.entities.Category;
import com.pradeep.blog.application.exception.ApiResponse;
import com.pradeep.blog.application.exception.ResourceNotFoundException;
import com.pradeep.blog.application.payloads.CategoryDto;
import com.pradeep.blog.application.repositories.CategoryRepository;
import com.pradeep.blog.application.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NonUniqueResultException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryHelper categoryHelper;

    @Autowired
    private CategoryRepository categoryRepository;


    private CategoryDto singleCategory(Integer categoryId) {
        return this.categoryHelper
                .convertCategoryToCategoryDto(this.categoryRepository.
                        findById(categoryId).
                        orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId)));
    }


    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = this.categoryRepository.findAll();

        if (categories.size() == 0) {
            throw new NullPointerException(CommonConstants.NO_CATEGORY_FOUND_MESSAGE);
        }

        List<CategoryDto> allCategories = categories.stream()
                .map((singleCategory) -> this.categoryHelper.convertCategoryToCategoryDto(singleCategory))
                .collect(Collectors.toList());

        return allCategories;
    }

    @Override
    public CategoryDto getSingleCategory(Integer categoryId) {
        return this.singleCategory(categoryId);
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category savedCategory;
        Category category = this.categoryRepository.findByCategoryTitle(categoryDto.getCategoryTitle());
        if (category == null) {
            savedCategory = this.categoryRepository.save(this.categoryHelper.convertCategoryDtoToCategory(categoryDto));
        } else {
            throw new NonUniqueResultException(CategoryConstants.CATEGORY_ALREADY_EXISTS);
        }
        return this.categoryHelper.convertCategoryToCategoryDto(savedCategory);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        CategoryDto category = this.singleCategory(categoryId);
        if (category != null) {
            this.categoryRepository.deleteById(categoryId);
        }
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {

        Category category = this.categoryRepository.findByCategoryTitle(categoryDto.getCategoryTitle());
        if (category != null) {
            throw new NonUniqueResultException(CategoryConstants.CATEGORY_ALREADY_EXISTS);
        }
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category savedCategory = this.categoryRepository.save(category);
        return this.categoryHelper.convertCategoryToCategoryDto(savedCategory);
    }

    @Override
    public List<CategoryDto> searchCategoryByTitle(String title) {

        List<Category> categories = this.categoryRepository.findByCategoryTitleContaining(title);
        if (categories.size() == 0) {
            throw new NullPointerException(CommonConstants.NO_CATEGORY_FOUND_BY_TITLE);
        }
        List<CategoryDto> categoryDtos = categories.stream().map((category) -> this.categoryHelper.convertCategoryToCategoryDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public List<CategoryDto> searchCategoryByDescription(String description) {

        List<Category> categories = this.categoryRepository.findByCategoryDescriptionContaining(description);
        if (categories.size() == 0) {
            throw new NullPointerException(CommonConstants.NO_CATEGORY_FOUND_BY_DESCRIPTION);
        }
        List<CategoryDto> categoryDtos = categories.stream().map((category) -> this.categoryHelper.convertCategoryToCategoryDto(category)).collect(Collectors.toList());
        return categoryDtos;
    }
}
