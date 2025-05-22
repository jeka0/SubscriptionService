package com.sub.SubscriptionService.service;

import com.sub.SubscriptionService.dto.SubscriptionDTO;

import java.util.List;
import java.util.Optional;

public interface SubscriptionService {
    SubscriptionDTO create(SubscriptionDTO subscriptionDTO);

    Optional<SubscriptionDTO> getById(Long id);

    List<SubscriptionDTO> findAllByUserId(Long userId);

    void deleteById(Long id);

    List<SubscriptionDTO> findTop3PopularSubscriptions();
}
