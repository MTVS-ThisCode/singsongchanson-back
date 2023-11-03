package com.singsongchanson.domain.user.command.application.dto;

import com.singsongchanson.domain.user.command.domain.aggregate.entity.User;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.Role;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.SocialType;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class FindUserResponseDTO {

    private Long userNo;
    private String email;
    private String profileImg;
    private String nickName;
    private String gender;
    private String ageRange;    // 연령대
    private String socialId;    // 로그인한 소셜 타입의 식별자 값
    private SocialType socialType;  // KAKAO, NAVER, GOOGLE
    private Role role;      // GUEST, USER, ADMIN

    public static FindUserResponseDTO from(User user) {

        return new FindUserResponseDTO(
                user.getUserNo(),
                user.getEmail(),
                user.getProfileImg(),
                user.getNickName(),
                user.getGender(),
                user.getAgeRange(),
                user.getSocialId(),
                user.getSocialType(),
                user.getRole()
        );
    }
}
