package com.sub.SubscriptionService.mapper;

import java.util.List;

public interface EntityMapper<D, E> {
    E toEntity(D dto);

    D toDto(E entity);

    E partialUpdate(E entity, D dto);

    List<E> toEntity(List<D> dtoList);

    List<D> toDto(List<E> entityList);
}
