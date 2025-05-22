package com.sub.SubscriptionService.controllers;

import com.sub.SubscriptionService.dto.UserDTO;
import com.sub.SubscriptionService.service.UserService;
import com.sub.SubscriptionService.validation.CreateGroup;
import com.sub.SubscriptionService.validation.UpdateGroup;
import org.apache.coyote.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/users")
public class UserController {

    private final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@Validated(CreateGroup.class) @RequestBody UserDTO userDTO) {
        log.info("REST request to create user: {}", userDTO);
        return ResponseEntity.ok(userService.create(userDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        log.info("REST request to get user: {}", id);
        return userService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @PathVariable Long id,
            @Validated(UpdateGroup.class) @RequestBody UserDTO userDTO
    ) throws BadRequestException {
        log.info("REST request to update user: {}", id);

        if(userDTO.getId() != null && !userDTO.getId().equals(id)){
            String message = "Path variable ID does not match the ID in the request body";
            log.error(message);
            throw new BadRequestException(message);
        }

        if(userDTO.getName() == null && userDTO.getEmail() == null) {
            String message = "Neither name nor email is provided — at least one field must be present to update the user";
            log.error(message);
            throw new BadRequestException(message);
        }

        userDTO.setId(id);
        return ResponseEntity.ok(userService.update(userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.info("REST request to delete user: {}", id);
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
