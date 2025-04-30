package com.be.ecommerce.service;

import com.be.ecommerce.model.Category;
import com.be.ecommerce.payload.CategoryDTO;
import com.be.ecommerce.payload.CategoryResponse;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    CategoryResponse getAllCategory(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);
    CategoryDTO createCategory(CategoryDTO categoryDTO);
    CategoryDTO deleteCategory(UUID categoryId);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, UUID categoryId);
}
