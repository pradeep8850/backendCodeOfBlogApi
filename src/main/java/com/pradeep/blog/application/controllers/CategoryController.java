package com.pradeep.blog.application.controllers;

import com.pradeep.blog.application.constants.category.CategoryConstants;
import com.pradeep.blog.application.exception.ApiResponse;
import com.pradeep.blog.application.payloads.CategoryDto;
import com.pradeep.blog.application.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blog/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    private ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategory = this.categoryService.createCategory(categoryDto);
        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/")
    private ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categories = this.categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/searchTitle/{title}")
    private ResponseEntity<List<CategoryDto>> searchCatByTitle(@PathVariable("title") String title) {
        List<CategoryDto> categories = this.categoryService.searchCategoryByTitle(title);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/searchDescription/{description}")
    private ResponseEntity<List<CategoryDto>> searchCatByDescription(@PathVariable("description") String description) {
        List<CategoryDto> categories = this.categoryService.searchCategoryByDescription(description);
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    private ResponseEntity<CategoryDto> getSingleCategory(@PathVariable("categoryId") Integer categoryId) {
        CategoryDto categoryDto = this.categoryService.getSingleCategory(categoryId);
        return ResponseEntity.ok(categoryDto);
    }

    @DeleteMapping("/{categoryId}")
    private ResponseEntity<ApiResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId) {
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse(CategoryConstants.CATEGORY_DELETE_MESSAGE, true), HttpStatus.OK);
    }

    @PutMapping("/{categoryId}")
    private ResponseEntity<CategoryDto> updateCategory(@RequestBody @Valid CategoryDto categoryDto, @PathVariable("categoryId") Integer categoryId) {
        CategoryDto createdCategory = this.categoryService.updateCategory(categoryDto, categoryId);
        return ResponseEntity.ok(createdCategory);

    }
}
