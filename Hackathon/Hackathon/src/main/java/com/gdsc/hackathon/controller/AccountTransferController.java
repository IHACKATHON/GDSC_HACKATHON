package com.gdsc.hackathon.controller;

import com.gdsc.hackathon.dto.TransferRequestDto;
import com.gdsc.hackathon.repository.AccountRepository;
import com.gdsc.hackathon.service.AccountTransferService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AccountTransferController {

    AccountTransferService accountTransferService;

    @PostMapping("/account/transfer")
    public ResponseEntity<String> transferFunds(@RequestBody TransferRequestDto transferRequestDto){
        accountTransferService.transferFunds(transferRequestDto);
        return ResponseEntity.ok("계좌 이체가 성공적으로 이루어졌습니다.");
    }
}
