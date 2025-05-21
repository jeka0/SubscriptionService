package com.sub.SubscriptionService.mapper.impl;

import com.sub.SubscriptionService.domain.User;
import com.sub.SubscriptionService.dto.UserDTO;
import com.sub.SubscriptionService.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserMapperImpl implements UserMapper {

    public UserDTO toDto(User entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(entity.getId());
        userDTO.setName(entity.getName());
        userDTO.setEmail(entity.getEmail());
        return userDTO;
    }

    public User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        return user;
    }

    public List<User> toEntity(List<UserDTO> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .toList();
    }

    public List<UserDTO> toDto(List<User> entityList){
        return entityList
                .stream()
                .map(this::toDto)
                .toList();
    }
}
