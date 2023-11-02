package com.singsongchanson.domain.user.query.application.service;

import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.SocialType;
import com.singsongchanson.domain.user.query.infrastructure.repository.UserQueryRepository;
import com.singsongchanson.global.security.jwt.domain.repository.TokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserQueryRepository userQueryRepository;
    private final TokenRepository tokenRepository;

    public FindUserResponseDTO findUserByUserNo(Long userNo) {

        FindUserResponseDTO findUserResponse = userQueryRepository.findById(userNo)
                .map(user -> FindUserResponseDTO.from(user))
                .orElse(null);

        return findUserResponse;
    }

    public FindUserResponseDTO findUserByEmail(String email) {

        FindUserResponseDTO findUserResponse = userQueryRepository.findByEmail(email)
                .map(user -> FindUserResponseDTO.from(user))
                .orElse(null);

        return findUserResponse;
    }

    public FindUserResponseDTO findUserSocialIdAndSocialType(String socialId, SocialType socialType) {

        FindUserResponseDTO findUserResponse = userQueryRepository.findBySocialIdAndSocialType(socialId, socialType)
                .map(user -> FindUserResponseDTO.from(user))
                .orElse(null);

        return findUserResponse;
    }

}
