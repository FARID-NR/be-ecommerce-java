package com.be.ecommerce.controller;

import com.be.ecommerce.config.AppConstants;
import com.be.ecommerce.payload.ProductDTO;
import com.be.ecommerce.payload.ProductResponse;
import com.be.ecommerce.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@Valid @RequestBody ProductDTO productDTO,
                                                 @PathVariable UUID categoryId){
        ProductDTO savedProductDTO = productService.addProduct(categoryId, productDTO);
        return new ResponseEntity<>(savedProductDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy",  defaultValue = AppConstants.SORT_PRODUCTS_BY) String sortBy,
            @RequestParam(name = "sortOrder",  defaultValue = AppConstants.SORT_DIR) String sortOrder
    ){
        ProductResponse productResponse = productService.getAllProducts(pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse , HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(
            @PathVariable UUID categoryId,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy",  defaultValue = AppConstants.SORT_PRODUCTS_BY) String sortBy,
            @RequestParam(name = "sortOrder",  defaultValue = AppConstants.SORT_DIR) String sortOrder
    ){
        ProductResponse productResponse = productService.searchByCategory(categoryId, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(
            @PathVariable String keyword,
            @RequestParam(name = "pageNumber", defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber,
            @RequestParam(name = "pageSize", defaultValue = AppConstants.PAGE_SIZE) Integer pageSize,
            @RequestParam(name = "sortBy",  defaultValue = AppConstants.SORT_PRODUCTS_BY) String sortBy,
            @RequestParam(name = "sortOrder",  defaultValue = AppConstants.SORT_DIR) String sortOrder
    ){

        ProductResponse productResponse = productService.searchProductByKeyword(keyword, pageNumber, pageSize, sortBy, sortOrder);
        return new ResponseEntity<>(productResponse , HttpStatus.OK);

    }

//    @PutMapping("/admin/products/{productId}")
//    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product,
//                                                    @PathVariable UUID productId) {
//        ProductDTO updateProductDTO = productService.updateProduct(productId, product);
//        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
//    }
    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO,
                                                    @PathVariable UUID productId) {
        ProductDTO updateProductDTO = productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deteleProducts(@PathVariable UUID productId){
        ProductDTO deteleProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deteleProduct, HttpStatus.OK);
    }

    @PutMapping("/products/{productId}/image")
    public ResponseEntity<ProductDTO> updateProductImage(@PathVariable UUID productId,
                                                         @RequestParam("image") MultipartFile image) throws IOException {
        ProductDTO updateProductImg = productService.updateProductImage(productId, image);
        return new ResponseEntity<>(updateProductImg, HttpStatus.OK);
    }
}
