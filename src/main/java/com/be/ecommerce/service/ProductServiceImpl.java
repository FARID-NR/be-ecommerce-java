package com.be.ecommerce.service;

import com.be.ecommerce.exceptions.APIException;
import com.be.ecommerce.exceptions.ResourceNotFoundException;
import com.be.ecommerce.model.Category;
import com.be.ecommerce.model.Product;
import com.be.ecommerce.payload.ProductDTO;
import com.be.ecommerce.payload.ProductResponse;
import com.be.ecommerce.repositories.CategoryRepository;
import com.be.ecommerce.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductDTO addProduct(UUID categoryId, Product product) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01)  * product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(UUID categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));

        List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductByKeyword(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');
        // bisa juga ini tapi dia akan mencari hanya dengan %{keyword}%
        // List<Product> products = productRepository.findByProductNameContainingIgnoreCase(keyword);
        List<ProductDTO> productDTOS = products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class)).toList();

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productDTOS);
        return productResponse;
    }

    @Override
    public ProductDTO updateProduct(UUID productId, Product product) {
        // Get the existing products from DB
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Products", "productId", productId));

        // Update the product info with the one in request body
        productFromDb.setProductName(product.getProductName());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setQuantity(product.getQuantity());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setDiscount(product.getDiscount());
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01)  * product.getPrice());
        productFromDb.setSpecialPrice(specialPrice);

        // Save to database
        Product saveProduct = productRepository.save(productFromDb);

        return modelMapper.map(saveProduct, ProductDTO.class);
    }

//    @Override
//    public ProductDTO updateProduct(UUID productId, ProductDTO productDTO) {
//        Product product = modelMapper.map(productDTO, Product.class);
//
//        Product updateProduct = productRepository.findById(productId)
//                .orElseThrow(() -> new ResourceNotFoundException("Products", "productId", productId));
//
//        product.setProductId(productId);
//
//        updateProduct = productRepository.save(product);
//        ProductDTO updateProductDTO = modelMapper.map(updateProduct, ProductDTO.class);
//        return updateProductDTO;
//    }

    @Override
    public ProductDTO deleteProduct(UUID productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Products", "productId", productId));

        productRepository.delete(product);
        return modelMapper.map(product, ProductDTO.class);
    }
}
