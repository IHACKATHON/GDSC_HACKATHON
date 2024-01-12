package com.gdsc.hackathon.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDataDto {
    private String email;
    private String accessToken;
}
