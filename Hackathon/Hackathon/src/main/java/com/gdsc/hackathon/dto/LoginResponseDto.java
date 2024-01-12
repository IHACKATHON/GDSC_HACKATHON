package com.gdsc.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String message;
    private int code;
    private UserDataDto data;

    public static LoginResponseDto success(int code, String message, String email, String accessToken) {
        return new LoginResponseDto(message, code, UserDataDto.builder().email(email).accessToken(accessToken).build());
    }

    public static LoginResponseDto failure(int code, String message) {
        return new LoginResponseDto(message, code, null);
    }
}
