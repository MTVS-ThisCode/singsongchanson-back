package com.singsongchanson.domain.music.command.domain.aggregate.entity;

import com.singsongchanson.domain.music.command.domain.aggregate.entity.enumtype.SongWriter;
import com.singsongchanson.domain.music.command.domain.aggregate.vo.GenerateUserVO;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "TBL_MUSIC")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class Music {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long musicNo;
    @Column
    private String title;    // 음악 제목            // 필기. AI 쪽에서 음악 제목이랑, 장르, url 세 가지 받기
    @Column
    private String genre;    // 음악 장르
    @Column
    private String musicUrl;
    @Column
    private String albumImgUrl;
    @Enumerated(EnumType.STRING)
    private SongWriter songWriter;     // 작곡가 정보
    @Embedded
    private GenerateUserVO generateUserVO;      // 생성 요청한 유저 정보

    @Builder
    public Music(String musicUrl, String albumImgUrl, SongWriter songWriter, GenerateUserVO generateUserVO) {
        this.musicUrl = musicUrl;
        this.albumImgUrl = albumImgUrl;
        this.songWriter = songWriter;
        this.generateUserVO = generateUserVO;
    }
}
