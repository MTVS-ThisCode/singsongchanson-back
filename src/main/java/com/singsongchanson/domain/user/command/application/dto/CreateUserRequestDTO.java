package com.singsongchanson.domain.user.command.application.dto;

import com.singsongchanson.domain.user.command.domain.aggregate.entity.User;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.Role;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.SocialType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class CreateUserRequestDTO {

    private String email;
    private String profileImg;
    private String nickName;
    private String gender;
    private String ageRange;
    private String socialId;
    private SocialType socialType;
    private Role role;

    public CreateUserRequestDTO(String email, String profileImg, String nickName, String gender, String ageRange, String socialId, SocialType socialType) {
        this.email = email;
        this.profileImg = profileImg;
        this.nickName = nickName;
        this.gender = gender;
        this.ageRange = ageRange;
        this.socialId = socialId;
        this.socialType = socialType;
        this.role = Role.USER;
    }

    public static User toEntity(CreateUserRequestDTO userDTO) {

        return new User(
                userDTO.getEmail(), userDTO.getProfileImg(), userDTO.getNickName(), userDTO.getGender(), userDTO.getAgeRange(), userDTO.getSocialId(),
                userDTO.getSocialType(), userDTO.getRole());
    }
}
