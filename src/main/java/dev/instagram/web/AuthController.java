package dev.instagram.web;

import dev.instagram.domain.member.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class AuthController {
    private final AuthService authService;

    @GetMapping({"/login" , "/"})
    public String LoginForm(){
        return "login";
    }

    @GetMapping("/signup")
    public String SignupForm(){
        return "signup";
    }

    @PostMapping("/signup")
    public String Signup(MemberDto memberDto){
        Member member = memberDto.toEntity();

        Member memberEntity = authService.signup(member);

        return "login";
    }
}
