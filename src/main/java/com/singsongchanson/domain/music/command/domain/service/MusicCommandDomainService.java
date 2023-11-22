package com.singsongchanson.domain.music.command.domain.service;

import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicResponseDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface MusicCommandDomainService {
     AiMusicResponseDTO getAiMusic(AiMusicRequestDTO aiMusicRequest);
     AiMusicResponseDTO getAiMusic(MultipartFile imageFile);
}
