    package com.gdsc.hackathon.controller;

    import com.gdsc.hackathon.domain.Member;
    import com.gdsc.hackathon.dto.*;
    import com.gdsc.hackathon.jwt.TokenProvider;
    import com.gdsc.hackathon.service.MemberService;
    import lombok.RequiredArgsConstructor;
    import org.springframework.http.HttpHeaders;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.access.prepost.PreAuthorize;
    import org.springframework.security.core.AuthenticationException;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.web.bind.annotation.*;

    import java.util.Optional;


    @RestController
    @RequestMapping("/api/member")
    @RequiredArgsConstructor
    public class MemberController {
        private final MemberService memberService;
        private final TokenProvider tokenProvider;

        @PostMapping("/signup/user") // localhost:8080/user/signup  일반 회원가입 ( 이메일, 비밀번호 포함 모든 정보 )
        public ResponseEntity<ApiResponseDto> signup(@RequestBody MemberRequestDto memberRequestDto){
            try {
                MemberResponseDto memberResponseDto = memberService.signup(memberRequestDto);
                return ResponseEntity.ok(ApiResponseDto.success(200, "데이터 처리 성공", memberResponseDto.getEmail()));
            } catch (Exception e) {
                return ResponseEntity.status(400).body(ApiResponseDto.failure(400, "회원가입 실패 메시지"));
            }
        }
        @PostMapping("/signup/business")
        public ResponseEntity<ApiResponseDto> businessSignup(@RequestBody BusinessUserRequestDto businessUserRequestDto){
            try {
                MemberResponseDto memberResponseDto = memberService.businessSignup(businessUserRequestDto);
                return ResponseEntity.ok(ApiResponseDto.success(200, "데이터 처리 성공", memberResponseDto.getEmail()));
            } catch (Exception e) {
                return ResponseEntity.status(400).body(ApiResponseDto.failure(400, "회원가입 실패 메시지"));
            }
        }

        @PostMapping("/login") // localhost:8080/user/login 로그인 ( 토큰 발급 )
        public ResponseEntity<ApiResponseDto> login(@RequestBody MemberRequestDto memberRequestDto) {
            try {
                TokenDto tokenDto = memberService.login(memberRequestDto);
                return ResponseEntity.ok(ApiResponseDto.success(200, "데이터 처리 성공", memberRequestDto.getEmail(), tokenDto.getAccessToken()));
            } catch (AuthenticationException e) {
                return ResponseEntity.status(401).body(ApiResponseDto.failure(401, "로그인 실패 메시지"));
            }

        }

        @GetMapping("/user") // localhost:8080/user    로그인 테스트
        public ResponseEntity<String> user(){
            return ResponseEntity.ok("Hello User");
        }


        @GetMapping("/email/{name}") // localhost:8080/user/email/{이름}   이름으로 이메일 찾기
        public ResponseEntity<String> findEmailByName(@PathVariable String name){
            String userEmail = memberService.findEmailByName(name);
            return ResponseEntity.ok(userEmail);
        }

        @GetMapping("/password/{email}")  // localhost:8080/user/password/{email}   이메일로 비밀번호 찾기 ( 이메일 인증 필수 )
        public ResponseEntity<String> findPasswordByEmail(@PathVariable String email){
            String userPassword = memberService.findPasswordByEmail(email);
            return ResponseEntity.ok(userPassword);
        }
        @PostMapping("/password")  // localhost:8080/user/password   비밀번호 변경  json { "currentPassword" : "현재 비밀번호 ", "newPassword" : "새로운 비밀번호" }
        @PreAuthorize("isAuthenticated()")
        public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDto changePasswordDto){
            try {
                memberService.changePassword(changePasswordDto);
                return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
            } catch (RuntimeException e){
                return ResponseEntity.badRequest().body(e.getMessage());
            }
        }
        @GetMapping("/user/info")
        public ResponseEntity<String> user(@RequestHeader(name = HttpHeaders.AUTHORIZATION) String authorizationHeader) {
            String accessToken = authorizationHeader.replace("Bearer ", "");

            if (tokenProvider.validateToken(accessToken)) {
                org.springframework.security.core.Authentication authentication = tokenProvider.getAuthentication(accessToken);
                UserDetails userDetails = (UserDetails) authentication.getPrincipal();

                String id = userDetails.getUsername();
                Optional<Member> optionalUser = memberService.getUserById(Long.parseLong(id));

                if (optionalUser.isPresent()) {
                    String useremail = optionalUser.get().getEmail();
                    String username = optionalUser.get().getName();
                    return ResponseEntity.ok("user name: " + username + "\nuser email: " + useremail);
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
                }
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid token");
            }
        }
        @GetMapping("/account/balance")
        public ResponseEntity<AccountBalanceDto> getAccountBalance() {
            int accountBalance = memberService.getAccountBalance();
            AccountBalanceDto accountBalanceDto = new AccountBalanceDto(accountBalance);
            return ResponseEntity.ok(accountBalanceDto);
        }

    }
