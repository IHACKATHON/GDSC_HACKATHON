package com.gdsc.hackathon.controller;

import com.gdsc.hackathon.dto.ResponseDto;
import com.gdsc.hackathon.dto.TransferRequestDto;
import com.gdsc.hackathon.dto.TransferResponseDto;
import com.gdsc.hackathon.repository.AccountRepository;
import com.gdsc.hackathon.service.AccountTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountTransferController {

    @Autowired
    AccountTransferService accountTransferService;

    @PostMapping("/account/transfer")
    public ResponseEntity<ResponseDto<TransferResponseDto>> transferFunds(@RequestBody TransferRequestDto transferRequestDto){
        TransferResponseDto responseDto = accountTransferService.transferFunds(transferRequestDto);
        ResponseDto<TransferResponseDto> responseData = new ResponseDto<>("데이터 처리 성공", 200, responseDto);
        return ResponseEntity.ok(responseData);
    }
}
