package com.singsongchanson.global.security.oauth2.service;

import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.domain.user.query.application.service.UserQueryService;
import com.singsongchanson.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserQueryService userQueryService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        FindUserResponseDTO user = userQueryService.findUserByEmail(email);
        return UserPrincipal.create(user);
    }


    public UserDetails loadUserById(Long id) {
        FindUserResponseDTO user = userQueryService.findUserByUserNo(id);
        return UserPrincipal.create(user);
    }
}
