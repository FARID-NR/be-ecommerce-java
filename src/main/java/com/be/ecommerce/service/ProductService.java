package com.be.ecommerce.service;

import com.be.ecommerce.model.Product;
import com.be.ecommerce.payload.ProductDTO;
import com.be.ecommerce.payload.ProductResponse;

import java.util.UUID;

public interface ProductService {
    ProductDTO addProduct(UUID categoryId, Product product);

    ProductResponse getAllProducts();

    ProductResponse searchProductByKeyword(String keyword);

    ProductResponse searchByCategory(UUID categoryId);

    ProductDTO updateProduct(UUID productId, Product product);

    ProductDTO deleteProduct(UUID productId);
}
