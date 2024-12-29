package com.abhishek.security.Cart;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CartItems {

          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          private int id;

          private int quantity;

          @ManyToOne
          @JoinColumn(name = "cart_id", nullable = false)
          private Cart cart;

          @Override
          public boolean equals(Object o) {
                    if (this == o)
                              return true;
                    if (o == null || getClass() != o.getClass())
                              return false;
                    CartItems that = (CartItems) o;
                    return id == that.id;
          }

          @Override
          public int hashCode() {
                    return Integer.hashCode(id);
          }
}
