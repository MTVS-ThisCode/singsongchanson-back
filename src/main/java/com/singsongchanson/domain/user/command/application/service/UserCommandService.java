package com.singsongchanson.domain.user.command.application.service;

import com.singsongchanson.domain.user.command.application.dto.CreateUserRequestDTO;
import com.singsongchanson.domain.user.command.application.dto.UpdateUserRequestDTO;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.User;
import com.singsongchanson.domain.user.command.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserCommandService {

    private final UserRepository userRepository;
    public User createUser(CreateUserRequestDTO createUserDTO) {

        User user = CreateUserRequestDTO.toEntity(createUserDTO);

        return userRepository.save(user);
    }

    public User updateUser(Long userNo, UpdateUserRequestDTO updateUserDTO) {

        Optional<User> findUser = userRepository.findByUserNo(userNo);

        if(findUser.isPresent()) {
            User user = findUser.get();
            user.update(updateUserDTO);

            return user;
        }

        return null;
    }
}
