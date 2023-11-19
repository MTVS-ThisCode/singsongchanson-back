package com.singsongchanson.domain.music.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class AiMusicRequestDTO {

    private String keyword;
}
