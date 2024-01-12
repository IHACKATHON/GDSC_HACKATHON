package com.gdsc.hackathon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferRequestDto {
    private String senderAccountNumber;
    private String receiverAccountNumber;
    private int amount;

}
