package com.abhishek.security.Cart;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.HashSet;
import java.util.Set;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cart {

          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          @EqualsAndHashCode.Include
          private int cartId;

          private int userId;

          @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
          @EqualsAndHashCode.Exclude
          private Set<CartItems> items = new HashSet<>();

          @Transactional
          public void addProduct(CartItems cartItems) {
                    CartItems existingItem = items.stream()
                                        .filter(item -> item.getId() == cartItems.getId())
                                        .findFirst()
                                        .orElse(null);
                    if (existingItem != null) {
                              existingItem.setQuantity(existingItem.getQuantity() + cartItems.getQuantity());
                    } else {
                              cartItems.setCart(this);
                              items.add(cartItems);
                    }
          }

          @Transactional
          public ResponseEntity<?> removeItem(int id) {
                    CartItems cartItems = items.stream()
                                        .filter(item -> item.getId() == id)
                                        .findFirst()
                                        .orElse(null);
                    if (cartItems != null) {
                              items.remove(cartItems);
                    }
                    return new ResponseEntity<>("No existing product!", HttpStatus.OK);

          }
}
