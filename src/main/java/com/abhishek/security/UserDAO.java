package com.abhishek.security;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import lombok.Data;

@Entity
@Data
public class UserDAO {
          @Id
          @GeneratedValue(strategy = GenerationType.AUTO)
          private int id;
          private String username;
          private String password;

}
