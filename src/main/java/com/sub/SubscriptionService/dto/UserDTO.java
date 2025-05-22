package com.sub.SubscriptionService.dto;

import java.io.Serializable;
import java.util.Objects;

import com.sub.SubscriptionService.validation.CreateGroup;
import com.sub.SubscriptionService.validation.UpdateGroup;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;

public class UserDTO implements Serializable {
    @Null(message = "Id must be null", groups = CreateGroup.class)
    private Long id;

    @NotNull(message = "Username cannot be null", groups = CreateGroup.class)
    private String name;

    @NotNull(message = "Email cannot be null", groups = CreateGroup.class)
    @Email(message = "Invalid email format", groups = {CreateGroup.class, UpdateGroup.class})
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
