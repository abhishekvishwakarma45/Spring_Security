package com.abhishek.security;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserDAO user) {
        return userService.register(user);
    }

    @GetMapping("/home")
    public String home(Principal principal) {
        System.out.println(principal.getName());
        return "home.html";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDAO userDAO) {
        String token = userService.verify(userDAO, authenticationManager);
        if ("fail".equals(token)) {
            return ResponseEntity.status(401).body("Authentication failed");
        }

        return ResponseEntity.ok(token);
    }
}
