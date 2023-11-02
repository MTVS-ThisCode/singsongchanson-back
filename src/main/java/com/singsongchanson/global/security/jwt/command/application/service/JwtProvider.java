package com.singsongchanson.global.security.jwt.command.application.service;

import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.global.configuration.AppProperties;
import com.singsongchanson.global.security.UserPrincipal;
import com.singsongchanson.global.security.jwt.domain.aggregate.entity.Token;
import com.singsongchanson.global.security.jwt.domain.aggregate.vo.UserVO;
import com.singsongchanson.global.security.jwt.domain.repository.TokenRepository;
import com.singsongchanson.global.security.jwt.domain.service.RequestUserDomainService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
@Getter
public class JwtProvider {

    private final AppProperties appProperties;
    private final TokenRepository tokenRepository;
    private final CustomTokenService customTokenService;
    private final RequestUserDomainService requestUserDomainService;

    /** AccessToken 생성 메소드 */
    public String issueTokenByUserPrincipal(UserPrincipal userPrincipal) {

        Long userNo = userPrincipal.getId();
        String userRole = userPrincipal.getRole();
        Optional<Token> findToken = tokenRepository.findByUserVO(new UserVO(userNo));
        String issuedToken = customTokenService.createToken(userNo, userRole);
        if (findToken.isPresent()) {
            Token updateToken = findToken.get();
            updateToken.setAccessToken(issuedToken);
            tokenRepository.save(updateToken);
        } else {
            Token createdToken = new Token(new UserVO(userNo), issuedToken);
            tokenRepository.save(createdToken);
        }

        return issuedToken;
    }

    public String issueTokenByAccessToken(String accessToken) {
        Token findToken = tokenRepository.findByAccessToken(accessToken).orElseThrow(
                () -> new NullPointerException("해당 Access Token은 폐기된 토큰입니다.")
        );
        Long userNo = findToken.getUserVO().getUserNo();

        FindUserResponseDTO findMember = requestUserDomainService.getUserByUserNo(userNo);

        String issuedToken = customTokenService.createToken(findToken.getId(), findMember.getRole().getKey());
        findToken.setAccessToken(issuedToken);
        tokenRepository.save(findToken);

        return issuedToken;
    }
}
