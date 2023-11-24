package com.singsongchanson.domain.music.command.application.dto;

import com.singsongchanson.domain.music.command.domain.aggregate.entity.Music;
import com.singsongchanson.domain.music.command.domain.aggregate.entity.enumtype.SongWriter;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MusicResponseDTO {

    private Long musicNo;
    private String title;
    private String genre;
    private String musicUrl;
    private String albumImgUrl;
    private Long streamingCnt;
    private SongWriter songWriter;
    private Long userNo;

    public static MusicResponseDTO from(Music music) {

        return new MusicResponseDTO(
                music.getMusicNo(),
                music.getTitle(),
                music.getGenre(),
                music.getMusicUrl(),
                music.getAlbumImgUrl(),
                music.getStreamingCnt(),
                music.getSongWriter(),
                music.getGenerateUserVO().getUserNo()
        );
    }
}
