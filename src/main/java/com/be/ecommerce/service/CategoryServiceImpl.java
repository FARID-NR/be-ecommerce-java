package com.be.ecommerce.service;

import com.be.ecommerce.model.Category;
import com.be.ecommerce.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
//    private List<Category> categories = new ArrayList<>();

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void createCategory(Category category) {
        category.setCategoryId(UUID.randomUUID());
        categoryRepository.save(category);
    }

    @Override
    public String deleteCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource not found"));

        categoryRepository.delete(category);
        return "Category with categoryId : " + categoryId + " delete successfully";
    }
    // INi kode ku
//    @Override
//    public String updateCategory(Category category, UUID categoryId) {
//        Category categoryUpdate = categories.stream()
//                .filter(c -> c.getCategoryId().equals(categoryId))
//                .findFirst().orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "UUID not found"));
//        categoryUpdate.setCategoryId(categoryId);
//        categoryUpdate.setCategoryName(category.getCategoryName());
//        return "Update Berhasil";
//    }


//    ini kode tutorial
    @Override
    public Category updateCategory(Category category, UUID categoryId) {

        Category savedCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Resource Not Found"));

        category.setCategoryId(categoryId);
        savedCategory = categoryRepository.save(category);
        return savedCategory;


    }

}
