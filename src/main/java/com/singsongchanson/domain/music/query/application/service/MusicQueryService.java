package com.singsongchanson.domain.music.query.application.service;

import com.singsongchanson.domain.music.command.application.dto.MusicResponseDTO;
import com.singsongchanson.domain.music.command.domain.aggregate.vo.GenerateUserVO;
import com.singsongchanson.domain.music.command.domain.repository.MusicRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MusicQueryService {

    private final MusicRepository musicRepository;
    public MusicResponseDTO findMusicByMusicNo(Long musicNo) {

        MusicResponseDTO musicResponse = musicRepository.findById(musicNo)
                .map(music -> MusicResponseDTO.from(music))
                .orElseThrow(() -> new IllegalArgumentException("해당하는 음악이 없습니다."));

        return musicResponse;
    }

    public List<MusicResponseDTO> findAllMusics() {

        List<MusicResponseDTO> musicList = musicRepository.findAll().stream()
                .map(MusicResponseDTO::from)
                .collect(Collectors.toList());

        return musicList;
    }

    public List<MusicResponseDTO> findMusicByGenerateUserVO(GenerateUserVO generateUserVO) {

        List<MusicResponseDTO> musicList = musicRepository.findByGenerateUserVO(generateUserVO).stream()
                .map(MusicResponseDTO::from)
                .collect(Collectors.toList());

        return musicList;
    }
}
