package com.abhishek.security.Product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Product {

          @Id
          private String id;
          private String name;
          private double price;
          private List<String> color;
          private List<String> image;
          @Column(name = "description", length = 1000)
          private String description;
          private String category;
          private boolean featured;

          private String createdBy;
}
