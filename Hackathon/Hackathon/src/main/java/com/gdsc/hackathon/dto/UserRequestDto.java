package com.gdsc.hackathon.dto;

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
public class UserRequestDto {

    private String email;
    private String password;
    private String name;
    private boolean isBusiness;

    public User toUser(PasswordEncoder passwordEncoder){
        return User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_USER)
                .name(name)
                .isBusiness(isBusiness)
                .build();
    }
    public User toGoogleUser(){
        return User.builder()
                .role(Role.ROLE_USER)
                .name(name)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication(){
        return new UsernamePasswordAuthenticationToken(email, password);
    }
}
