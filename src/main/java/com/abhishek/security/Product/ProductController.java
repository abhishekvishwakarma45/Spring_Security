package com.abhishek.security.Product;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.abhishek.security.Cart.CartService;

@Controller
@RequestMapping("/product")
public class ProductController {

          @Autowired
          private ProductService productService;

          @PostMapping("/add")
          public ResponseEntity<?> addProduct(@RequestBody Product product, Principal principal) {
                    if (principal == null || principal.getName() == null) {
                              return ResponseEntity.status(403).body("User is not authenticated");

                    }

                    String username = principal.getName();
                    System.out.println("username:" + username);
                    return productService.addProduct(product, username);
          }

          @GetMapping("/get")
          public ResponseEntity<List<Product>> GetProducts(Principal principal) {
                    if (principal == null || principal.getName() == null) {
                              System.err.println("user is not authenticated");
                    }

                    String username = principal.getName();
                    System.out.println("user :" + username);
                    return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.FOUND);
          }

}
