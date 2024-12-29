package com.abhishek.security.Cart;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
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

}
