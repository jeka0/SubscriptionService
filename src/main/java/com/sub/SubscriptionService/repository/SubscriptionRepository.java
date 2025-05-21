package com.sub.SubscriptionService.repository;

import com.sub.SubscriptionService.domain.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    @Query("select subscription from Subscription subscription where subscription.user.id =:userId")
    List<Subscription> findAllByUserId(@Param("userId") Long userId);
}
