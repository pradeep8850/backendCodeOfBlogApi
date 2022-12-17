package com.pradeep.blog.application.constants.helper;

import com.pradeep.blog.application.entities.Category;
import com.pradeep.blog.application.payloads.CategoryDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryHelper {
    @Autowired
    private ModelMapper modelMapper;

    public CategoryDto convertCategoryToCategoryDto(Category category) {
        return this.modelMapper.map(category, CategoryDto.class);
    }

    public Category convertCategoryDtoToCategory(CategoryDto categoryDto) {
        return this.modelMapper.map(categoryDto, Category.class);
    }
}
