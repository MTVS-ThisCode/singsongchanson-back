package com.singsongchanson.domain.music.query.application.controller;

import com.singsongchanson.domain.music.command.application.dto.MusicResponseDTO;
import com.singsongchanson.domain.music.command.domain.aggregate.vo.GenerateUserVO;
import com.singsongchanson.domain.music.query.application.service.MusicQueryService;
import com.singsongchanson.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/musics")
@RequiredArgsConstructor
public class MusicQueryController {

    private final MusicQueryService musicQueryService;

    /** 음악 전체 조회 */
    @GetMapping
    public ApiResponse findMusics() {

        List<MusicResponseDTO> musicResponse = musicQueryService.findAllMusics();

        return ApiResponse.success("성공적으로 조회되었습니다.", musicResponse);
    }

    /** 음악 상세 조회 */
    @GetMapping("/{musicNo}")
    public ApiResponse findMusic(@PathVariable Long musicNo) {

        MusicResponseDTO musicResponse = musicQueryService.findMusicByMusicNo(musicNo);

        return ApiResponse.success("성공적으로 조회되었습니다.", musicResponse);
    }

//    /** 내 음악 조회 */
//    @GetMapping("/myMusic")
//    public ApiResponse findMusicByUserNo(@AuthenticationPrincipal UserPrincipal userPrincipal) {
//
//        List<MusicResponseDTO> musicResponse = musicQueryService.findMusicByGenerateUserVO(new GenerateUserVO(userPrincipal.getId()));
//
//        return ApiResponse.success("성공적으로 조회되었습니다.", musicResponse);
//    }

    /** userNo로 음악 상세 조회 */
    @GetMapping("/myMusic")
    public ApiResponse findMusicByUserNo(@RequestParam Long userNo) {

        List<MusicResponseDTO> musicResponse = musicQueryService.findMusicByGenerateUserVO(new GenerateUserVO(userNo));

        return ApiResponse.success("성공적으로 조회되었습니다.", musicResponse);
    }
}
