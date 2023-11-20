package com.singsongchanson.domain.music.command.infrastructure.service;

import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicResponseDTO;
import com.singsongchanson.domain.music.command.domain.service.MusicCommandDomainService;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.HashMap;
import java.util.Map;

@Service
public class MusicCommandInfraService implements MusicCommandDomainService {

    private String baseIp = "http://221.163.19.218:7777";

    @Override
    public AiMusicResponseDTO getAiMusic(AiMusicRequestDTO aiMusicRequest) {

        Map<String, String> aiMusicRequestMap = new HashMap<>();
        aiMusicRequestMap.put("keyword", aiMusicRequest.getKeyword());

        WebClient webClient = WebClient.builder()
                .baseUrl(baseIp + "/generate_music")
                .build();

        AiMusicResponseDTO aiMusicResponse = webClient.post()
                .bodyValue(aiMusicRequestMap)
                .retrieve()
                .bodyToMono(AiMusicResponseDTO.class)
                .block();

//        System.out.println("aiMusicResponse = " + aiMusicResponse);

        boolean result = aiMusicResponse.getResult();

        if (result) {
            return aiMusicResponse;
        } else {
            throw new IllegalArgumentException("통신 실패!");
        }
    }
}
