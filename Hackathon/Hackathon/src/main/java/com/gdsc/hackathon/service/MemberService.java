package com.gdsc.hackathon.service;


import com.gdsc.hackathon.domain.Account;
import com.gdsc.hackathon.dto.*;
import com.gdsc.hackathon.jwt.TokenProvider;
import com.gdsc.hackathon.repository.AccountRepository;
import com.gdsc.hackathon.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final MailService mailService;
    private final AccountRepository accountRepository;

    @Transactional
    public MemberResponseDto signup(MemberRequestDto memberRequestDto){
        if(memberRepository.existsByEmail(memberRequestDto.getEmail())){
            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
        }
        com.gdsc.hackathon.domain.Member member = memberRequestDto.toUser(passwordEncoder);
        return MemberResponseDto.of(this.memberRepository.save(member));
    }

    @Transactional
    public MemberResponseDto businessSignup(BusinessUserRequestDto businessUserRequestDto){
        if(memberRepository.existsByEmail(businessUserRequestDto.getEmail())){
            throw new RuntimeException("이미 가입되어 있는 이메일입니다.");
        }
        com.gdsc.hackathon.domain.Member member = businessUserRequestDto.toBusinessUser(passwordEncoder);
        return MemberResponseDto.of(this.memberRepository.save(member));
    }

    @Transactional
    public TokenDto login(MemberRequestDto memberRequestDto){
        UsernamePasswordAuthenticationToken authenticationToken = memberRequestDto.toAuthentication();

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        TokenDto tokenDto = tokenProvider.generateTokenDto(authentication);

        return tokenDto;
    }

    @Transactional
    public String findEmailByName(String name){
        com.gdsc.hackathon.domain.Member member = this.memberRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("해당하는 이름의 사용자를 찾을 수 없습니다."));
        return member.getEmail();
    }
    @Transactional
    public Optional<com.gdsc.hackathon.domain.Member> getUserById(long id){
        return memberRepository.findUserById(id);
    }

    @Transactional
    public String findPasswordByEmail(String email){
        com.gdsc.hackathon.domain.Member member = this.memberRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("해당하는 이메일의 사용자를 찾을 수 없습니다."));
        String temporaryPassword = mailService.sendPassword(email);
        member.setPassword(passwordEncoder.encode(temporaryPassword));
        this.memberRepository.save(member);
        return temporaryPassword;
    }


    @Transactional
    public String makeRandomPassword() {
        int length = 8;
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder temporaryPassword = new StringBuilder();

        for(int i = 0; i < length; i++){
            int index = (int) (Math.random() * characters.length());
            temporaryPassword.append(characters.charAt(index));
        }
        return temporaryPassword.toString();
    }

    @Transactional
    public void changePassword(ChangePasswordDto changePasswordDto){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        String currentPassword = changePasswordDto.getCurrentPassword();
        String newPassword = changePasswordDto.getNewPassword();

        com.gdsc.hackathon.domain.Member member = this.memberRepository.findById(Long.valueOf(userEmail))
                .orElseThrow(() -> new RuntimeException("현재 로그인한 사용자를 찾을 수 없습니다."));
        if(!passwordEncoder.matches(currentPassword, member.getPassword())){
            throw new RuntimeException("현재 비밀번호가 일치하지 않습니다.");
        }
        member.setPassword(passwordEncoder.encode(newPassword));
        this.memberRepository.save(member);
    }

    @Transactional
    public int getAccountBalance() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();

        com.gdsc.hackathon.domain.Member member = this.memberRepository.findById(Long.valueOf(userEmail))
                .orElseThrow(() -> new RuntimeException("현재 로그인한 사용자를 찾을 수 없습니다."));

        Account account = member.getAccount();
        return account.getBalance();
    }
}
