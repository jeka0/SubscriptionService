package com.sub.SubscriptionService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@Controller
@RequestMapping("/")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/users")
    public ResponseEntity<String> createUser() throws URISyntaxException {
        log.info("REST request to create user");

        return ResponseEntity
                .ok()
                .body("hello world");
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUser(@PathVariable Long id) {
        log.info("REST request to get user : {}", id);

        return ResponseEntity.ok("get");
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id) throws URISyntaxException {
        log.info("REST request to update user : {}", id);

        return ResponseEntity
                .ok()
                .body("put");
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("REST request to delete user : {}", id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
