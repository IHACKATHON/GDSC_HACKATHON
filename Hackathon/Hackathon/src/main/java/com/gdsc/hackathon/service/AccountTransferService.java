package com.gdsc.hackathon.service;

import com.gdsc.hackathon.domain.Account;
import com.gdsc.hackathon.dto.TransferRequestDto;
import com.gdsc.hackathon.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountTransferService {

    private final AccountRepository accountRepository;

    @Transactional
    public void transferFunds(TransferRequestDto transferRequestDto){
        Account senderAccount = accountRepository.findByAccountNumber(transferRequestDto.getSenderAccountNumber());
        Account receiverAccount = accountRepository.findByAccountNumber(transferRequestDto.getReceiverAccountNumber());

        if(senderAccount == null || receiverAccount == null){
            throw new IllegalArgumentException("계좌 정보를 확인할 수 없습니다.");
        }
        if(senderAccount.getBalance() < transferRequestDto.getAmount()){
            throw new IllegalArgumentException("잔액이 부족합니다.");
        }
        senderAccount.setBalance(senderAccount.getBalance() - transferRequestDto.getAmount());
        senderAccount.setBalance(senderAccount.getBalance() - transferRequestDto.getAmount());
    }
}
