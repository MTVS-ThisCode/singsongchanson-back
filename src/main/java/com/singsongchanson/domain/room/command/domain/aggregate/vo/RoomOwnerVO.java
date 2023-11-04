package com.singsongchanson.domain.room.command.domain.aggregate.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class RoomOwnerVO {

    private Long userNo;

    public RoomOwnerVO(Long userNo) {
        this.userNo = userNo;
    }
}
