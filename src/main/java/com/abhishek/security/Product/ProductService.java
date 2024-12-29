package com.abhishek.security.Product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

          @Autowired
          private ProductRepository productRepository;

          public ResponseEntity<?> addProduct(Product product, String username) {
                    product.setCreatedBy(username);
                    productRepository.save(product);
                    return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
          }

          public List<Product> getAllProducts() {
                    List<Product> allProducts = productRepository.findAll();
                    return allProducts;
          }
}
