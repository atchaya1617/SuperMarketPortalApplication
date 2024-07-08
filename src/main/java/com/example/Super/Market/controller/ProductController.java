package com.example.Super.Market.controller;


import com.example.Super.Market.entity.Product;
import com.example.Super.Market.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;


    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product request) {
        Product product = new Product();
        product.setProductName(request.getProductName());
        product.setProductPrice(request.getProductPrice());
        product.setMfgDate(request.getMfgDate());
        product.setExpDate(request.getExpDate());
        Product createdProduct= productService.createProduct(product);

        return ResponseEntity.ok(createdProduct);
    }


    @PutMapping("/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product) {
        Product updatedProduct = productService.update(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

    @DeleteMapping("/deleteProduct")
    public ResponseEntity<Product> deleteProduct(@RequestParam String productId) {
        Product deleteProduct = productService.deleteById(productId);
        return new ResponseEntity<>(deleteProduct, HttpStatus.OK);
    }

    @GetMapping("/getProduct")
    public ResponseEntity<Product> getProductById(@RequestParam String productId){
        Product product = productService.getById(productId);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Product>> getAllProducts(){
        List<Product> product = productService.getAll();
        return ResponseEntity.ok(product);

    }

}
