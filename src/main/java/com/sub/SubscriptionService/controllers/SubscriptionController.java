package com.sub.SubscriptionService.controllers;

import com.sub.SubscriptionService.dto.SubscriptionDTO;
import com.sub.SubscriptionService.service.SubscriptionService;
import com.sub.SubscriptionService.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class SubscriptionController {

    private final Logger log = LoggerFactory.getLogger(SubscriptionController.class);

    private final UserService userService;
    private final SubscriptionService subscriptionService;

    public SubscriptionController(UserService userService, SubscriptionService subscriptionService) {
        this.userService = userService;
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/users/{id}/subscriptions")
    public ResponseEntity<SubscriptionDTO> addSubscriptionToUser(
            @PathVariable Long id,
            @Validated @RequestBody SubscriptionDTO subscriptionDTO
    ) {
        log.info("REST request to add subscription to user {}: {}", id, subscriptionDTO);
        SubscriptionDTO result = userService.addSubscriptionToUser(id, subscriptionDTO);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/users/{id}/subscriptions")
    public ResponseEntity<List<SubscriptionDTO>> getUserSubscriptions(@PathVariable Long id) {
        log.info("REST request to get subscriptions of user {}", id);
        return ResponseEntity.ok(userService.getUserSubscriptions(id));
    }

    @DeleteMapping("/users/{id}/subscriptions/{subId}")
    public ResponseEntity<Void> deleteUserSubscription(
            @PathVariable Long id,
            @PathVariable Long subId
    ) {
        log.info("REST request to remove subscription {} from user {}", subId, id);
        userService.removeSubscriptionFromUser(id, subId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/subscriptions/top")
    public ResponseEntity<List<SubscriptionDTO>> getTopSubscriptions() {
        log.info("REST request to get top 3 subscriptions");
        return ResponseEntity.ok(subscriptionService.findTop3PopularSubscriptions());
    }
}
