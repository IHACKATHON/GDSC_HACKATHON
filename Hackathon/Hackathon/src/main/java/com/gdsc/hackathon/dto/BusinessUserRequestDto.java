package com.gdsc.hackathon.dto;

import com.gdsc.hackathon.domain.Account;
import com.gdsc.hackathon.domain.Role;
import com.gdsc.hackathon.domain.User;
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
    public User toBusinessUser(PasswordEncoder passwordEncoder) {
        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_BUSINESS)
                .name(name)
                .build();

        Account account = new Account();
        account.setAccountNumber(accountNumber);
        account.setBalance(balance);
        account.setUser(user);

        user.setAccount(account);


        return user;
    }


    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
