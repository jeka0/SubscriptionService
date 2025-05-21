package com.sub.SubscriptionService.dto;


import java.io.Serializable;
import java.util.Objects;
import jakarta.annotation.Nonnull;

public class UserDTO implements Serializable {
    @Nonnull
    private Long id;

    @Nonnull
    private String name;

    @Nonnull
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDTO)) {
            return false;
        }

        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, ((UserDTO) o).id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + getId() +
                ", name='" + getName() + "'" +
                ", email='" + getEmail() + "'" +
                "}";
    }
}
