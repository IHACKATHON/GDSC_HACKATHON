// ResponseDto.java

package com.gdsc.hackathon.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto<T> {

    private String message;
    private int code;
    private T data;

    public ResponseDto(String message, int code, T data) {
        this.message = message;
        this.code = code;
        this.data = data;
    }
}
