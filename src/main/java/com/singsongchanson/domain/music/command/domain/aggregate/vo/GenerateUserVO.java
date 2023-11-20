package com.singsongchanson.domain.music.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class GenerateUserVO {

    @Column
    private Long userNo;

    public GenerateUserVO(Long userNo) {
        this.userNo = userNo;
    }
}
