package com.singsongchanson.domain.asset.command.application.controller;


import com.singsongchanson.domain.asset.command.application.dto.AssetResponseDTO;
import com.singsongchanson.domain.asset.command.application.service.AssetCommandService;
import com.singsongchanson.global.common.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/s3")
@RequiredArgsConstructor
public class AssetCommandController {

    private final AssetCommandService assetCommandService;

    @PostMapping("/upload")
    public ApiResponse uploadFile(@RequestParam MultipartFile file,
                                  @RequestParam String gender,
                                  @RequestParam double scale) throws IOException {

        AssetResponseDTO assetResponse = assetCommandService.saveFile(file, gender, scale);

        return ApiResponse.success("성공적으로 저장되었습니다.", assetResponse);
    }


}
