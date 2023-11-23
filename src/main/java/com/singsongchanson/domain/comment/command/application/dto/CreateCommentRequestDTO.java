package com.singsongchanson.domain.comment.command.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class CreateCommentRequestDTO {

    private String content;
    private String roomId;
}
