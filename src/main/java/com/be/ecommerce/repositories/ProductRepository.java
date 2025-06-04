package com.be.ecommerce.repositories;

import com.be.ecommerce.model.Category;
import com.be.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    List<Product> findByCategoryOrderByPriceAsc(Category category);

    List<Product> findByProductNameLikeIgnoreCase(String keyword);
    List<Product> findByProductNameContainingIgnoreCase(String keyword);
}
