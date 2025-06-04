package com.be.ecommerce.controller;

import com.be.ecommerce.config.AppConstants;
import com.be.ecommerce.model.Product;
import com.be.ecommerce.payload.ProductDTO;
import com.be.ecommerce.payload.ProductResponse;
import com.be.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductDTO> addProduct(@RequestBody Product product,
                                                 @PathVariable UUID categoryId){
        ProductDTO productDTO = productService.addProduct(categoryId, product);
        return new ResponseEntity<>(productDTO, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts(){
        ProductResponse productResponse = productService.getAllProducts();
        return new ResponseEntity<>(productResponse , HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable UUID categoryId){
        ProductResponse productResponse = productService.searchByCategory(categoryId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword){

        ProductResponse productResponse = productService.searchProductByKeyword(keyword);
        return new ResponseEntity<>(productResponse , HttpStatus.OK);

    }

    @PutMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody Product product,
                                                    @PathVariable UUID productId) {
        ProductDTO updateProductDTO = productService.updateProduct(productId, product);
        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
    }
//    @PutMapping("/admin/products/{productId}")
//    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO,
//                                                    @PathVariable UUID productId) {
//        ProductDTO updateProductDTO = productService.updateProduct(productId, productDTO);
//        return new ResponseEntity<>(updateProductDTO, HttpStatus.OK);
//    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductDTO> deteleProducts(@PathVariable UUID productId){
        ProductDTO deteleProduct = productService.deleteProduct(productId);
        return new ResponseEntity<>(deteleProduct, HttpStatus.OK);
    }
}
