package com.singsongchanson.global.security.oauth2;

import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.SocialType;
import com.singsongchanson.global.security.oauth2.userInfo.KakaoOAuth2UserInfo;
import com.singsongchanson.global.security.oauth2.userInfo.OAuth2UserInfo;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private String nameAttributeKey; // OAuth2 로그인 진행 시 키가 되는 필드 값, PK와 같은 의미
    private OAuth2UserInfo oauth2UserInfo; // 소셜 타입별 로그인 유저 정보(닉네임, 이메일, 프로필 사진 등등)

    @Builder
    public OAuthAttributes(String nameAttributeKey, OAuth2UserInfo oauth2UserInfo) {
        this.nameAttributeKey = nameAttributeKey;
        this.oauth2UserInfo = oauth2UserInfo;
    }

    /**
     * SocialType에 맞는 메소드 호출하여 OAuthAttributes 객체 반환
     * 파라미터 : userNameAttributeName -> OAuth2 로그인 시 키(PK)가 되는 값 / attributes : OAuth 서비스의 유저 정보들
     * 소셜별 of 메소드(ofGoogle, ofKaKao, ofNaver)들은 각각 소셜 로그인 API에서 제공하는
     * 회원의 식별값(id), attributes, nameAttributeKey를 저장 후 build
     */
    public static OAuthAttributes of(SocialType socialType,
                                     String userNameAttributeName, Map<String, Object> attributes) {

//        if (socialType == SocialType.NAVER) {
//            return ofNaver(userNameAttributeName, attributes);
//        }
//        if (socialType == SocialType.GOOGLE) {
//            return ofGoogle(userNameAttributeName, attributes);
//        }
        return ofKakao(userNameAttributeName, attributes);
    }

    private static OAuthAttributes ofKakao(String userNameAttributeName, Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .nameAttributeKey(userNameAttributeName)
                .oauth2UserInfo(new KakaoOAuth2UserInfo(attributes))
                .build();
    }

//    public static OAuthAttributes ofGoogle(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .nameAttributeKey(userNameAttributeName)
//                .oauth2UserInfo(new GoogleOAuth2UserInfo(attributes))
//                .build();
//    }
//
//    public static OAuthAttributes ofNaver(String userNameAttributeName, Map<String, Object> attributes) {
//        return OAuthAttributes.builder()
//                .nameAttributeKey(userNameAttributeName)
//                .oauth2UserInfo(new NaverOAuth2UserInfo(attributes))
//                .build();
//    }
}
