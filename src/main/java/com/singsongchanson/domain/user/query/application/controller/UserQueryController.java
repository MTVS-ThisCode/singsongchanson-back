package com.singsongchanson.domain.user.query.application.controller;

import com.singsongchanson.domain.user.command.application.dto.FindUserResponseDTO;
import com.singsongchanson.domain.user.query.application.service.UserQueryService;
import com.singsongchanson.global.common.response.ApiResponse;
import com.singsongchanson.global.security.UserPrincipal;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "UserQueryController", description = "회원 API Document")
public class UserQueryController {

    private final UserQueryService userQueryService;

    @Operation(summary = "유저 정보 조회 요청", description = "유저 정보를 조회합니다.", tags = { "UserQueryController" })
    @GetMapping("/info")
    public ApiResponse findUser(@AuthenticationPrincipal UserPrincipal userPrincipal) {

        Long userNo = userPrincipal.getId();
        FindUserResponseDTO findUserResponse = userQueryService.findUserByUserNo(userNo);

        if(findUserResponse == null) {
            throw new IllegalArgumentException("해당하는 회원이 없습니다.");
        }

        return ApiResponse.success("성공적으로 조회되었습니다", findUserResponse);
    }
}
