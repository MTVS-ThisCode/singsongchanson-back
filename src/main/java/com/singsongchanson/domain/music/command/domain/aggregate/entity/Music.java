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
    @Column
    private Long streamingCnt;      // 재생 횟수
    @Enumerated(EnumType.STRING)
    private SongWriter songWriter;     // 작곡가 정보
    @Embedded
    private GenerateUserVO generateUserVO;      // 생성 요청한 유저 정보

    public Music(String musicUrl, String albumImgUrl, Long streamingCnt, SongWriter songWriter, GenerateUserVO generateUserVO) {
        this.musicUrl = musicUrl;
        this.albumImgUrl = albumImgUrl;
        this.streamingCnt = streamingCnt;
        this.songWriter = songWriter;
        this.generateUserVO = generateUserVO;
    }

    public void updateStreamingCnt(Long streamingCnt) {
        this.streamingCnt = streamingCnt;
    }
}
