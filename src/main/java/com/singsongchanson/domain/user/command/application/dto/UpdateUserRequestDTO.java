package com.singsongchanson.domain.user.command.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class UpdateUserRequestDTO {

    private String profileImg;
    private String nickName;

    public UpdateUserRequestDTO(String profileImg, String nickName) {
        this.profileImg = profileImg;
        this.nickName = nickName;
    }

    public UpdateUserRequestDTO setProfileImg(String profileImg) {
        this.profileImg = profileImg;
        return this;
    }

    public UpdateUserRequestDTO setNickName(String nickName) {
        this.nickName = nickName;
        return this;
    }
}
