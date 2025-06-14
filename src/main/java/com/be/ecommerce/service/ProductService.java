package com.be.ecommerce.service;

import com.be.ecommerce.model.Product;
import com.be.ecommerce.payload.ProductDTO;
import com.be.ecommerce.payload.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public interface ProductService {
    ProductDTO addProduct(UUID categoryId, ProductDTO productDTO);

    ProductResponse getAllProducts(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse searchProductByKeyword(String keyword, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductResponse searchByCategory(UUID categoryId, Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    ProductDTO updateProduct(UUID productId, ProductDTO productDTO);

    ProductDTO deleteProduct(UUID productId);

    ProductDTO updateProductImage(UUID productId, MultipartFile image) throws IOException;
}
