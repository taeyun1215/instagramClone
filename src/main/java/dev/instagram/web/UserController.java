package dev.instagram.web;

import dev.instagram.JWT.JwtTokenDto;
import dev.instagram.JWT.JwtTokenProvider;
import dev.instagram.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @GetMapping("/login")
    public String LoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> Login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberLoginDto.getEmail(), memberLoginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);


        Member loginMember = userService.login(memberLoginDto);

        return new ResponseEntity<>(new JwtTokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/signup")
    public String SignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String Signup(@RequestBody MemberSignupDto memberSignUpDto) throws Exception {
        if (!Objects.equals(memberSignUpDto.getPassword1(), memberSignUpDto.getPassword2())) {
            throw new Exception("비밀번호와 비밀번호 확인이 같지 않습니다.");
        }

        userService.signup(memberSignUpDto);
        return "login";
    }

}
