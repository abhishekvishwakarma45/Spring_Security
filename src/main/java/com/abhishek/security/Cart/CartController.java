package com.abhishek.security.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartController {

          @Autowired
          CartService cartService;

          @PostMapping("/add")
          public ResponseEntity<?> addProductToCart(@RequestBody CartItems cartItems, Authentication authentication) {
                    if (authentication == null || authentication.getName() == null) {
                              return ResponseEntity.status(403).body("User is not authenticated");
                    }
                    String username = authentication.getName();
                    return cartService.addProductToCart(cartItems, username);
          }

          @GetMapping("/get/{id}")
          public ResponseEntity<?> getCartProducts(@PathVariable int id, Authentication authentication) {
                    if (authentication.getName() == null) {
                              return new ResponseEntity<>("User is not authenticated!", HttpStatus.BAD_REQUEST);
                    }
                    String username = authentication.getName();
                    return cartService.getCartProduct(username, id);
          }

          @DeleteMapping("/remove/{id}")
          public ResponseEntity<?> removeProduct(@PathVariable int id, Authentication authentication) {
                    if (authentication.getName() == null) {
                              return new ResponseEntity<>("User is not authenticated!", HttpStatus.BAD_REQUEST);
                    }
                    String username = authentication.getName();
                    return cartService.removeProduct(id, username);
          }
}
