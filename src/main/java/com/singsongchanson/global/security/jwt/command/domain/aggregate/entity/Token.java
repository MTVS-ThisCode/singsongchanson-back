package com.singsongchanson.global.security.jwt.command.domain.aggregate.entity;

import com.singsongchanson.global.security.jwt.command.domain.aggregate.vo.UserVO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "TBL_TOKEN")
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private UserVO userVO;

    @Column(length = 1024, nullable = false)
    private String accessToken;

    public Token(UserVO userVO, String accessToken) {
        this.userVO = userVO;
        this.accessToken = accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}
