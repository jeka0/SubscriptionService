package com.sub.SubscriptionService.service.impl;

import com.sub.SubscriptionService.dto.SubscriptionDTO;
import com.sub.SubscriptionService.mapper.SubscriptionMapper;
import com.sub.SubscriptionService.repository.SubscriptionRepository;
import com.sub.SubscriptionService.service.SubscriptionService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    private final Logger log = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

    private final SubscriptionMapper subscriptionMapper;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionServiceImpl(
            SubscriptionMapper subscriptionMapper,
            SubscriptionRepository subscriptionRepository
    ) {
        this.subscriptionMapper = subscriptionMapper;
        this.subscriptionRepository = subscriptionRepository;
    }

    public SubscriptionDTO create(SubscriptionDTO subscriptionDTO) {
        log.info("Creating subscription: {}", subscriptionDTO);
        return subscriptionMapper.toDto(subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionDTO)));
    }

    public Optional<SubscriptionDTO> getById(Long id) {
        log.info("Fetching subscription by id: {}", id);
        return subscriptionRepository.findById(id).map(subscriptionMapper::toDto);
    }

    public List<SubscriptionDTO> findAllByUserId(Long userId) {
        log.info("Fetching all subscriptions for userId: {}", userId);
        return subscriptionMapper.toDto(subscriptionRepository.findAllByUserId(userId));
    }

    public void deleteById(Long id) {
        log.info("Deleting subscription by id: {}", id);
        subscriptionRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Subscription not found: {}", id);
                    return new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subscription not found");
                });
        subscriptionRepository.deleteById(id);
    }

    public List<SubscriptionDTO> findTop3PopularSubscriptions() {
        log.info("Fetching top 3 popular subscriptions");
        Pageable topThree = PageRequest.of(0, 3);
        return subscriptionRepository.findPopularSubscriptions(topThree)
                .stream()
                .map(subscriptionMapper::toDto)
                .toList();
    }
}

