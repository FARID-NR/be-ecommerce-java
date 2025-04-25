package com.be.ecommerce.service;

import com.be.ecommerce.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    List<Category> getAllCategory();
    void createCategory(Category category);
    String deleteCategory(UUID categoryId);
    Category updateCategory(Category category, UUID categoryId);
}
