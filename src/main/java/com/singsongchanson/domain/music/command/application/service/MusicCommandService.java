package com.singsongchanson.domain.music.command.application.service;

import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicResponseDTO;
import com.singsongchanson.domain.music.command.application.dto.MusicResponseDTO;
import com.singsongchanson.domain.music.command.domain.aggregate.entity.Music;
import com.singsongchanson.domain.music.command.domain.aggregate.entity.enumtype.SongWriter;
import com.singsongchanson.domain.music.command.domain.aggregate.vo.GenerateUserVO;
import com.singsongchanson.domain.music.command.domain.repository.MusicCommandRepository;
import com.singsongchanson.domain.music.command.domain.service.MusicCommandDomainService;
import com.singsongchanson.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MusicCommandService {

    private final MusicCommandRepository musicCommandRepository;
    private final MusicCommandDomainService musicCommandDomainService;

    public MusicResponseDTO saveAiMusic(UserPrincipal userPrincipal, AiMusicRequestDTO aiMusicRequest) {

        AiMusicResponseDTO aiMusicResponse = musicCommandDomainService.getAiMusic(aiMusicRequest);

        System.out.println("aiMusicResponse = " + aiMusicResponse);
        System.out.println("userPrincipal = " + userPrincipal);

        Music music = Music.builder()
//                .title(aiMusicResponse.getTitle())
//                .genre(aiMusicResponse.getGenre())
                .musicUrl(aiMusicResponse.getMusic_path())
                .albumImgUrl(aiMusicResponse.getImage_path())
                .songWriter(SongWriter.AI)
                .generateUserVO(new GenerateUserVO(userPrincipal.getId()))
                .build();

        System.out.println("music = " + music);
        musicCommandRepository.save(music);

        return MusicResponseDTO.from(music);
    }
}
