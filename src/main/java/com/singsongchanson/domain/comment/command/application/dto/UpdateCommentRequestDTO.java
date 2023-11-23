package com.singsongchanson.domain.comment.command.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class UpdateCommentRequestDTO {

    private String content;
}
