package com.abhishek.security.Cart;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.transaction.Transactional;
import lombok.Data;

@Entity
@Data

public class Cart {

          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private int cartId;

          private int userId;

          @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
          Set<CartItems> items = new HashSet<CartItems>();

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

}
