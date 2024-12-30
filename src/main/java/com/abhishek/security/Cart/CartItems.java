package com.abhishek.security.Cart;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CartItems {

          @Id
          @GeneratedValue(strategy = GenerationType.IDENTITY)
          @EqualsAndHashCode.Include
          private int id;

          private int quantity;
          private String productName;

          @ManyToOne
          @JoinColumn(name = "cartId", nullable = false)
          @JsonBackReference
          private Cart cart;
}
