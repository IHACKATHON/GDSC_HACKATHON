package com.gdsc.hackathon.dto;

import com.gdsc.hackathon.domain.Account;
import com.gdsc.hackathon.domain.Bank;
import com.gdsc.hackathon.domain.Role;
import com.gdsc.hackathon.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BusinessUserRequestDto {

    private String email;
    private String password;
    private String name;
    private String accountNumber;
    private int balance;
    private Bank bank;
    public Member toBusinessUser(PasswordEncoder passwordEncoder) {
        Member member = Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_BUSINESS)
                .name(name)
                .bank(bank)
                .build();

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setMember(member);

        member.setAccount(account);


        return member;
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
