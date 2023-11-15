package com.singsongchanson.global.security.jwt.command.domain.aggregate.vo;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class UserVO {

    @Column(nullable = false)
    private Long userNo;

    protected UserVO() {
    }

    public UserVO(Long userNo) {
        this.userNo = userNo;
    }
}
