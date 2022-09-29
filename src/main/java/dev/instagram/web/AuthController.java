package dev.instagram.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
@Slf4j
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public String LoginForm() {
        return "login";
    }

    @GetMapping("/signup")
    public String SignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String Signup(@RequestBody MemberDto memberDto) throws Exception {
        authService.signup(memberDto);

        return "login";
    }
}
