package com.pradeep.blog.application.payloads;

import com.pradeep.blog.application.constants.category.CategoryConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class CategoryDto {
    private Integer categoryId;

    @NotEmpty
    @NotNull
    @Size(min = CategoryConstants.CATEGORY_TITLE_MIN_LENGTH, max = CategoryConstants.CATEGORY_TITLE_MAX_LENGTH, message = CategoryConstants.CATEGORY_TITLE_LENGTH_MESSAGE)
    private String categoryTitle;

    @NotEmpty
    @NotNull
    @Size(min = CategoryConstants.CATEGORY_DESCRIPTION_MIN_LENGTH, max = CategoryConstants.CATEGORY_DESCRIPTION_MAX_LENGTH, message = CategoryConstants.CATEGORY_DESCRIPTION_LENGTH_MESSAGE)
    private String categoryDescription;

}
