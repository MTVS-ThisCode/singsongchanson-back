package com.singsongchanson.domain.music.command.application.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AiMusicRequestDTO {

    private String keyword;
    private int duration;
    private String scale;
    private String instrument;
    private String genre;
}
