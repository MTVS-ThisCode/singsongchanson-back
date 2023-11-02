package com.singsongchanson.global.common.response;

import com.singsongchanson.global.common.response.enumtype.ApiStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ApiResponse {

    private ApiStatus status;
    private String message;
    private Object data;

    public ApiResponse(ApiStatus status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static  ApiResponse success(String message, Object data) {
        return new ApiResponse(ApiStatus.SUCCESS, message, data);
    }

    public static ApiResponse error(String message, Object data) {
        return new ApiResponse(ApiStatus.ERROR, message, data);
    }
}
