package com.sub.SubscriptionService.service;

import com.sub.SubscriptionService.dto.SubscriptionDTO;
import com.sub.SubscriptionService.dto.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserService {
    UserDTO create(UserDTO userDTO);

    Optional<UserDTO> getById(Long id);

    UserDTO update(UserDTO userDTO);

    void deleteById(Long id);

    SubscriptionDTO addSubscriptionToUser(Long userId, SubscriptionDTO subscriptionDTO);

    List<SubscriptionDTO> getUserSubscriptions(Long userId);

    void removeSubscriptionFromUser(Long userId, Long subscriptionId);
}
