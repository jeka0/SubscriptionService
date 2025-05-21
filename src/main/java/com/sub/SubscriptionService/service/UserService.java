package com.sub.SubscriptionService.service;

import com.sub.SubscriptionService.dto.UserDTO;

import java.util.Optional;

public interface UserService {
    UserDTO create(UserDTO userDTO);

    Optional<UserDTO> getById(Long id);

    UserDTO update(UserDTO userDTO);

    void deleteById(Long id);
}
