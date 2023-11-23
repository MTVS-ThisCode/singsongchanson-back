package com.singsongchanson.domain.comment.command.domain.entity.vo;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Embeddable
public class CommentUserVO {

    private Long userNo;
    private String nickName;
    private String profileImg;

    public CommentUserVO(Long userNo, String nickName, String profileImg) {
        this.userNo = userNo;
        this.nickName = nickName;
        this.profileImg = profileImg;
    }
}
