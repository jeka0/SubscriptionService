package com.sub.SubscriptionService.service.impl;

import com.sub.SubscriptionService.domain.User;
import com.sub.SubscriptionService.dto.UserDTO;
import com.sub.SubscriptionService.mapper.UserMapper;
import com.sub.SubscriptionService.repository.UserRepository;
import com.sub.SubscriptionService.service.UserService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(
            UserMapper userMapper,
            UserRepository userRepository
    ){
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public UserDTO create(UserDTO userDTO){
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));
    }

    public Optional<UserDTO> getById(Long id){
        Optional<User> user = userRepository.findById(id);
        if(user.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(userMapper.toDto(user.get()));
    }

    public UserDTO update(UserDTO userDTO){
        return userMapper.toDto(userRepository.save(userMapper.toEntity(userDTO)));
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }
}
