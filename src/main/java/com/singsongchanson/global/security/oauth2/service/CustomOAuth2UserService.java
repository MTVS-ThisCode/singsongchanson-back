package com.singsongchanson.global.security.oauth2.service;

import com.singsongchanson.domain.user.command.application.dto.CreateUserRequestDTO;
import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.domain.user.command.application.dto.UpdateUserRequestDTO;
import com.singsongchanson.domain.user.command.application.service.UserCommandService;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.User;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.SocialType;
import com.singsongchanson.domain.user.query.application.service.UserQueryService;
import com.singsongchanson.global.security.UserPrincipal;
import com.singsongchanson.global.security.oauth2.userInfo.KakaoOAuth2UserInfo;
import com.singsongchanson.global.security.oauth2.userInfo.OAuth2UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService  {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    private SocialType getSocialType(String registrationId) {
        if(registrationId.equals("naver")) {
            return SocialType.NAVER;
        }
        if(registrationId.equals("google")) {
            return SocialType.GOOGLE;
        }
        return SocialType.KAKAO;
    }

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        String registrationId = oAuth2UserRequest.getClientRegistration().getRegistrationId(); // 소셜 정보 가져옴
        SocialType socialType = getSocialType(registrationId);

        String userNameAttributeName = oAuth2UserRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        OAuth2UserInfo attributes = getOAuth2UserInfo(registrationId, oAuth2User.getAttributes());
        return saveOrUpdate(attributes, socialType);
    }

    private UserPrincipal saveOrUpdate(OAuth2UserInfo attributes, SocialType socialType) {
        FindUserResponseDTO user = userQueryService.findUserSocialIdAndSocialType(attributes.getSocialId(), socialType);
        UserPrincipal oauthMember;
        if (user == null) {
            CreateUserRequestDTO createUserDTO = new CreateUserRequestDTO(
                    attributes.getEmail(), attributes.getProfileImg(), attributes.getNickname(),
                    attributes.getGender(), attributes.getAgeRange(), attributes.getSocialId(), socialType);
            User newUser = userCommandService.createUser(createUserDTO);
            oauthMember = UserPrincipal.create(newUser, attributes.getAttributes());
        }
        else {
            UpdateUserRequestDTO updateUserDTO = new UpdateUserRequestDTO(attributes.getProfileImg(), attributes.getNickname());
            User updateUser = userCommandService.updateUser(user.getUserNo(), updateUserDTO);
            oauthMember = UserPrincipal.create(updateUser, attributes.getAttributes());
        }
        return oauthMember;
    }

    private OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {

        if(registrationId.equalsIgnoreCase(SocialType.KAKAO.name())) {
            return new KakaoOAuth2UserInfo(attributes);
        } else {
            throw new IllegalArgumentException("해당 OAuth2 제공자는 지원하지 않습니다");
        }
    }
}
