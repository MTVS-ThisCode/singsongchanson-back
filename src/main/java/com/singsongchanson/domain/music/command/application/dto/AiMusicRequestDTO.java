package com.singsongchanson.domain.music.command.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AiMusicRequestDTO {

    private String keyword;
}
