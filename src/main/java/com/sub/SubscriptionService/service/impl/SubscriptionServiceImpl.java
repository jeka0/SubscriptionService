package com.sub.SubscriptionService.service.impl;

import com.sub.SubscriptionService.dto.SubscriptionDTO;
import com.sub.SubscriptionService.mapper.SubscriptionMapper;
import com.sub.SubscriptionService.repository.SubscriptionRepository;
import com.sub.SubscriptionService.service.SubscriptionService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public SubscriptionDTO create(SubscriptionDTO subscriptionDTO){
        return subscriptionMapper.toDto(subscriptionRepository.save(subscriptionMapper.toEntity(subscriptionDTO)));
    }

    public List<SubscriptionDTO> findAllByUserId(Long userId){
        return subscriptionMapper.toDto(subscriptionRepository.findAllByUserId(userId));
    }

    public void deleteById(Long id){
        subscriptionRepository.deleteById(id);
    }
}
