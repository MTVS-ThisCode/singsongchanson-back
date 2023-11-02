package com.singsongchanson.domain.user.command.domain.aggregate.entity;

import com.singsongchanson.domain.user.command.application.dto.UpdateUserRequestDTO;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.Role;
import com.singsongchanson.domain.user.command.domain.aggregate.entity.enumtype.SocialType;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_USER")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userNo;
    @Column
    private String email;
    @Column
    private String profileImg;
    @Column
    private String nickName;
    @Column
    private String gender;
    @Column
    private String ageRange;    // 연령대
    @Column
    private String socialId;    // 로그인한 소셜 타입의 식별자 값
    @Enumerated(EnumType.STRING)
    private SocialType socialType;  // KAKAO, NAVER, GOOGLE
    @Enumerated(EnumType.STRING)
    private Role role;      // GUEST, USER, ADMIN

    @Builder
    public User(String email, String profileImg, String nickName, String gender, String ageRange, String socialId, SocialType socialType, Role role) {
        this.email = email;
        this.profileImg = profileImg;
        this.nickName = nickName;
        this.gender = gender;
        this.ageRange = ageRange;
        this.socialId = socialId;
        this.socialType = socialType;
        this.role = role;
    }

//    // 설명. 유저 권한 설정 메소드
//    public void authorizeUser() {
//        this.role = Role.USER;
//    }

//    // 설명. 리프레시 토큰 재발급 메소드
//    public void updateAccessToken(String accessToken) {
//        this.accessToken = accessToken;
//    }

    public void update(UpdateUserRequestDTO updateDTO) {
        this.profileImg = updateDTO.getProfileImg();
        this.nickName = updateDTO.getNickName();
    }
}
