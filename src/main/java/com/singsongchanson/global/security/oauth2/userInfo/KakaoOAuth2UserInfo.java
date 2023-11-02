package com.singsongchanson.global.security.oauth2.userInfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {
    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
    Map<String, Object> profile = (Map<String, Object>) account.get("profile");

    @Override
    public String getEmail() {
        if (account == null || profile == null) {
            return null;
        }

        return (String) account.get("email");    }

    @Override
    public String getProfileImg() {

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("thumbnail_image_url");    }

    @Override
    public String getNickname() {

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("nickname");
    }

    @Override
    public String getGender() {
        if (account == null || profile == null) {
            return null;
        }

        return (String) account.get("gender");
    }

    @Override
    public String getAgeRange() {
        if (account == null || profile == null) {
            return null;
        }

        return (String) account.get("age_range");    }

    @Override
    public String getSocialId() {
        return String.valueOf(attributes.get("id"));
    }
}
