package com.sub.SubscriptionService.repository;

import com.sub.SubscriptionService.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    @Query("SELECT s FROM Subscription s JOIN s.users u WHERE u.id = :userId")
    List<Subscription> findAllByUserId(Long userId);

    Optional<Subscription> findByServiceName(String serviceName);

    @Query("SELECT s FROM Subscription s LEFT JOIN s.users u GROUP BY s ORDER BY COUNT(u) DESC")
    List<Subscription> findPopularSubscriptions(Pageable pageable);
}
