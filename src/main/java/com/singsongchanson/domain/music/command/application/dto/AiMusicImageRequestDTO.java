package com.singsongchanson.domain.music.command.application.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class AiMusicImageRequestDTO {

    private String title;
    private MultipartFile imageFile;

}
