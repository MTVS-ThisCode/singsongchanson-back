package com.singsongchanson.domain.music.command.application.service;

import com.singsongchanson.domain.music.command.application.dto.AiMusicImageRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicResponseDTO;
import com.singsongchanson.domain.music.command.application.dto.MusicResponseDTO;
import com.singsongchanson.domain.music.command.domain.aggregate.entity.Music;
import com.singsongchanson.domain.music.command.domain.aggregate.entity.enumtype.SongWriter;
import com.singsongchanson.domain.music.command.domain.aggregate.vo.GenerateUserVO;
import com.singsongchanson.domain.music.command.domain.repository.MusicRepository;
import com.singsongchanson.domain.music.command.domain.service.MusicCommandDomainService;
import com.singsongchanson.global.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MusicCommandService {

    private final MusicRepository musicRepository;
    private final MusicCommandDomainService musicCommandDomainService;

    public MusicResponseDTO saveAiMusic(UserPrincipal userPrincipal, AiMusicRequestDTO aiMusicRequest) {

        AiMusicResponseDTO aiMusicResponse = musicCommandDomainService.getAiMusic(aiMusicRequest);

        Music music = new Music(
                aiMusicRequest.getTitle(),
                aiMusicRequest.getGenre(),
                aiMusicResponse.getMusic_path(),
                aiMusicResponse.getImage_path(),
                0L,
                SongWriter.AI,
                new GenerateUserVO(userPrincipal.getId())
        );

        musicRepository.save(music);

        return MusicResponseDTO.from(music);
    }

    public MusicResponseDTO saveAiMusicByImage(UserPrincipal userPrincipal, AiMusicImageRequestDTO aiMusicImageRequest) {

        AiMusicResponseDTO aiMusicResponse = musicCommandDomainService.getAiMusic(aiMusicImageRequest);

        Music music = new Music(
                aiMusicImageRequest.getTitle(),
                aiMusicResponse.getMusic_path(),
                aiMusicResponse.getImage_path(),
                0L,
                SongWriter.AI,
                new GenerateUserVO(userPrincipal.getId())
        );

        musicRepository.save(music);

        return MusicResponseDTO.from(music);
    }

    public MusicResponseDTO updateStreamingCnt(Long musicNo) {

       Optional<Music> optionalMusic = musicRepository.findById(musicNo);

       if (optionalMusic.isPresent()) {
           Music music = optionalMusic.get();
           Long newCount = music.getStreamingCnt() + 1;
           music.updateStreamingCnt(newCount);
           musicRepository.save(music);

           return MusicResponseDTO.from(music);
       }

        return null;    // 낫파운드 리팩토링
    }
}
