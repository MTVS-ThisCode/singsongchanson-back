package com.singsongchanson.domain.music.command.domain.service;

import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicResponseDTO;
import org.springframework.stereotype.Service;

@Service
public interface MusicCommandDomainService {
     AiMusicResponseDTO getAiMusic(AiMusicRequestDTO aiMusicRequest);
}
