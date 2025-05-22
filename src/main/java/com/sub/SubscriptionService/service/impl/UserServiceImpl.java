package com.sub.SubscriptionService.service.impl;

import com.sub.SubscriptionService.domain.Subscription;
import com.sub.SubscriptionService.domain.User;
import com.sub.SubscriptionService.dto.SubscriptionDTO;
import com.sub.SubscriptionService.dto.UserDTO;
import com.sub.SubscriptionService.mapper.SubscriptionMapper;
import com.sub.SubscriptionService.mapper.UserMapper;
import com.sub.SubscriptionService.repository.SubscriptionRepository;
import com.sub.SubscriptionService.repository.UserRepository;
import com.sub.SubscriptionService.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;
    private final SubscriptionMapper subscriptionMapper;
    private final UserRepository userRepository;
    private final SubscriptionRepository subscriptionRepository;

    public UserServiceImpl(
            UserMapper userMapper,
            SubscriptionMapper subscriptionMapper,
            UserRepository userRepository,
            SubscriptionRepository subscriptionRepository
    ) {
        this.userMapper = userMapper;
        this.subscriptionMapper = subscriptionMapper;
        this.userRepository = userRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public UserDTO create(UserDTO userDTO) {
        log.info("Creating user: {}", userDTO);
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));
    }

    public Optional<UserDTO> getById(Long id) {
        log.info("Fetching user by id: {}", id);
        return Optional.of(userMapper.toDto(getUserById(id)));
    }

    public UserDTO update(UserDTO userDTO) {
        log.info("Updating user: {}", userDTO);

        User existingUser = getUserById(userDTO.getId());

        User newUser = userMapper.partialUpdate(existingUser, userDTO);

        return userMapper.toDto(userRepository.save(newUser));
    }

    public void deleteById(Long id) {
        log.info("Deleting user by id: {}", id);
        getUserById(id);
        userRepository.deleteById(id);
    }

    public SubscriptionDTO addSubscriptionToUser(Long userId, SubscriptionDTO subscriptionDTO) {
        log.info("Adding subscription '{}' to user with id {}", subscriptionDTO.getServiceName(), userId);

        User user = getUserById(userId);

        Subscription subscription = subscriptionRepository
                .findByServiceName(subscriptionDTO.getServiceName())
                .orElseGet(() -> {
                    log.info("Creating new subscription: {}", subscriptionDTO.getServiceName());
                    return subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionDTO));
                });

        user.getSubscriptions().add(subscription);
        subscription.getUsers().add(user);

        userRepository.save(user);

        log.info("Successfully added subscription to user");
        return subscriptionMapper.toDto(subscription);
    }

    public List<SubscriptionDTO> getUserSubscriptions(Long userId) {
        log.info("Fetching subscriptions for userId: {}", userId);
        User user = getUserById(userId);

        return user.getSubscriptions().stream()
                .map(subscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    public void removeSubscriptionFromUser(Long userId, Long subscriptionId) {
        log.info("Removing subscription {} from user {}", subscriptionId, userId);

        User user = getUserById(userId);

        Subscription subscription = subscriptionRepository.findById(subscriptionId)
                .orElseThrow(() -> {
                    log.error("Subscription not found: {}", subscriptionId);
                    return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription not found");
                });

        user.getSubscriptions().remove(subscription);
        subscription.getUsers().remove(user);

        userRepository.save(user);
        log.info("Successfully removed subscription from user");
    }

    private User getUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> {
                    log.error("User not found: {}", userId);
                    return new ResponseStatusException(HttpStatus.BAD_REQUEST, "User not found");
                });
    }
}

