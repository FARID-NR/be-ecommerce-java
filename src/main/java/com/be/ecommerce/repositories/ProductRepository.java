package com.be.ecommerce.repositories;

import com.be.ecommerce.model.Category;
import com.be.ecommerce.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

    Page<Product> findByCategoryOrderByPriceAsc(Category category, Pageable pageable);

    Page<Product> findByProductNameLikeIgnoreCase(String keyword, Pageable pageable);
    List<Product> findByProductNameContainingIgnoreCase(String keyword);
}
