package com.example.Super.Market.service;

import com.example.Super.Market.entity.Product;
import com.example.Super.Market.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductSequenceService sequenceService;

    @Transactional
    public Product createProduct(Product product) {
        String productId = String.valueOf(sequenceService.getNextSequenceValue("product_sequence"));
        product.setProductId("00" + productId);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }


    public Product update(Product product){
        return productRepository.saveAndFlush(product);
    }

    public Product deleteById(String productId) {
        Optional<Product> productOptional = productRepository.findById(productId);

        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            productRepository.deleteById(productId);
        } else {
            throw new RuntimeException("Product not found with id " + productId);
        }
        return null;
    }

    public Product getById(String productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            return optionalProduct.get();
        }else{
            throw new EntityNotFoundException("not found");
        }
    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }

}
