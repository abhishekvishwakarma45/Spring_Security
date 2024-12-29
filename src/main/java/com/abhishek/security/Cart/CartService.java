package com.abhishek.security.Cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.abhishek.security.UserDAO;
import com.abhishek.security.UserRepository;

@Service
public class CartService {

          @Autowired
          private UserRepository userRepository;

          @Autowired
          private CartRepository cartRepository;

          public ResponseEntity<String> addProductToCart(CartItems cartItems, String username) {
                    UserDAO userDao = userRepository.findUserByUsername(username);
                    if (userDao == null) {
                              return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
                    }
                    Cart cart = cartRepository.findByUserId(userDao.getId());

                    if (cart == null) {
                              cart = new Cart();
                              cart.setUserId(userDao.getId());
                    }

                    cart.addProduct(cartItems);
                    cartRepository.save(cart);
                    return new ResponseEntity<>("Product added successfully", HttpStatus.CREATED);
          }
}