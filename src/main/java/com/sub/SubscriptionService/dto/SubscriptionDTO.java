package com.sub.SubscriptionService.dto;

import jakarta.annotation.Nonnull;

import java.io.Serializable;
import java.util.Objects;

public class SubscriptionDTO implements Serializable {
    private Long id;

    @Nonnull
    private String serviceName;

    @Nonnull
    private UserDTO user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SubscriptionDTO)) {
            return false;
        }

        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ((SubscriptionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "SubscriptionDTO{" +
                "id=" + getId() +
                ", serviceName='" + getServiceName() + "'" +
                ", user=" + getUser() +
                "}";
    }
}
