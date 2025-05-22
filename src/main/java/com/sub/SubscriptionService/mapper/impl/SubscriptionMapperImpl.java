package com.sub.SubscriptionService.mapper.impl;

import com.sub.SubscriptionService.domain.Subscription;
import com.sub.SubscriptionService.dto.SubscriptionDTO;
import com.sub.SubscriptionService.mapper.SubscriptionMapper;
import com.sub.SubscriptionService.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionMapperImpl implements SubscriptionMapper {

    private final UserMapper userMapper;

    public SubscriptionMapperImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public SubscriptionDTO toDto(Subscription entity) {
        SubscriptionDTO subscriptionDTO = new SubscriptionDTO();
        subscriptionDTO.setId(entity.getId());
        subscriptionDTO.setServiceName(entity.getServiceName());
        return subscriptionDTO;
    }

    public Subscription toEntity(SubscriptionDTO dto) {
        Subscription subscription = new Subscription();
        subscription.setId(dto.getId());
        subscription.setServiceName(dto.getServiceName());
        return subscription;
    }

    public Subscription partialUpdate(Subscription entity, SubscriptionDTO dto){
        Subscription subscription = new Subscription();
        subscription.setId(entity.getId());
        subscription.setServiceName(dto.getServiceName() != null ? dto.getServiceName() : entity.getServiceName());
        return subscription;
    }

    public List<Subscription> toEntity(List<SubscriptionDTO> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .toList();
    }

    public List<SubscriptionDTO> toDto(List<Subscription> entityList){
        return entityList
                .stream()
                .map(this::toDto)
                .toList();
    }
}
