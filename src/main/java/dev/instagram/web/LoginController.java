package dev.instagram.web;

import dev.instagram.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Controller
public class LoginController {
    private final LoginService loginService;

    @GetMapping("/login")
    public String LoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String Login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {
        loginService.login(memberLoginDto);
        return "";
    }
}
