package com.singsongchanson.domain.music.command.application.dto;

import com.singsongchanson.domain.music.command.domain.aggregate.entity.Music;
import com.singsongchanson.domain.music.command.domain.aggregate.entity.enumtype.SongWriter;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class MusicRoomResponseDTO {

    private Long musicNo;
    private String title;
    private String genre;
    private String musicUrl;
    private String albumImgUrl;
    private Long streamingCnt;
    private SongWriter songWriter;
    private Long userNo;
    private String roomId;
}
