package com.gdsc.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponseDto {
    private String message;
    private int code;
    private UserDataDto data;

    public static ApiResponseDto success(int code, String message, String email) {
        return new ApiResponseDto(message, code, UserDataDto.builder().email(email).build());
    }

    public static ApiResponseDto success(int code, String message, String email, String accessToken) {
        return new ApiResponseDto(message, code, UserDataDto.builder().email(email).accessToken(accessToken).build());
    }

    public static ApiResponseDto failure(int code, String message) {
        return new ApiResponseDto(message, code, null);
    }
}
