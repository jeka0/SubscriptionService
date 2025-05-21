package com.sub.SubscriptionService.service;

import com.sub.SubscriptionService.dto.SubscriptionDTO;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDTO create(SubscriptionDTO subscriptionDTO);

    List<SubscriptionDTO> findAllByUserId(Long userId);

    void deleteById(Long id);
}
