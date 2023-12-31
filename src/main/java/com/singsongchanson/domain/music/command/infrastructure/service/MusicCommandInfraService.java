package com.singsongchanson.domain.music.command.infrastructure.service;

import com.singsongchanson.domain.music.command.application.dto.AiMusicImageRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicRequestDTO;
import com.singsongchanson.domain.music.command.application.dto.AiMusicResponseDTO;
import com.singsongchanson.domain.music.command.domain.service.MusicCommandDomainService;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class MusicCommandInfraService implements MusicCommandDomainService {

    private String baseIp = "http://221.163.19.218:7777";

    @Override
    public AiMusicResponseDTO getAiMusic(AiMusicRequestDTO aiMusicRequest) {

        WebClient webClient = WebClient.builder()
                .baseUrl(baseIp + "/generate_music")
                .build();

        AiMusicResponseDTO aiMusicResponse = webClient.post()
                .bodyValue(aiMusicRequest)
                .retrieve()
                .bodyToMono(AiMusicResponseDTO.class)
                .block();

        System.out.println("aiMusicResponse = " + aiMusicResponse);

        boolean result = aiMusicResponse.getResult();

        if (result) {
            return aiMusicResponse;
        } else {
            throw new IllegalArgumentException("통신 실패!");
        }
    }

    @Override
    public AiMusicResponseDTO getAiMusic(AiMusicImageRequestDTO aiMusicImageRequest) {

        MultipartBodyBuilder builder = new MultipartBodyBuilder();
        builder.part("file", aiMusicImageRequest.getImageFile().getResource());

        WebClient webClient = WebClient.builder()
                .baseUrl(baseIp + "/image_generate_music")
                .build();

        AiMusicResponseDTO aiMusicResponse = webClient.post()
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(builder.build()))
                .retrieve()
                .bodyToMono(AiMusicResponseDTO.class)
                .block();

        System.out.println("aiMusicResponse = " + aiMusicResponse);

        boolean result = aiMusicResponse.getResult();

        if (result) {
            return aiMusicResponse;
        } else {
            throw new IllegalArgumentException("통신 실패!");
        }
    }
}
