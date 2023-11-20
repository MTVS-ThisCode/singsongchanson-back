package com.singsongchanson.domain.music.command.application.dto;

import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class AiMusicResponseDTO {

    private String music_path;
    private String image_path;
    private boolean result;
    //    private String title;
    //    private String genre;

    protected AiMusicResponseDTO() {}

    public AiMusicResponseDTO(String music_path, String image_path, boolean result) {
        this.music_path = music_path;
        this.image_path = image_path;
        this.result = result;
    }

    public String getMusic_path() {
        return music_path;
    }

    public String getImage_path() {
        return image_path;
    }

    public boolean getResult() {
        return result;
    }
}
