package com.sub.SubscriptionService.controllers;

import com.sub.SubscriptionService.dto.UserDTO;
import com.sub.SubscriptionService.service.UserService;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Nonnull;

import java.net.URISyntaxException;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Nonnull @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.info("REST request to create user: {}", userDTO);

        UserDTO createdUser =  userService.create(userDTO);

        return ResponseEntity
                .ok()
                .body(createdUser);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) throws BadRequestException {
        log.info("REST request to get user : {}", id);

        Optional<UserDTO> userDTO = userService.getById(id);

        if(userDTO.isEmpty()){
            throw new BadRequestException("User with id:%s not found".formatted(id));
        }

        return ResponseEntity
                .ok()
                .body(userDTO.get());
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @Nonnull @RequestBody UserDTO userDTO
    ) throws URISyntaxException {
        log.info("REST request to update user : {}", id);

        UserDTO updatedUser = userService.update(userDTO);

        return ResponseEntity
                .ok()
                .body(updatedUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("REST request to delete user : {}", id);
        userService.deleteById(id);
        return ResponseEntity
                .noContent()
                .build();
    }
}
