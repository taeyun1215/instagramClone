package dev.instagram.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String LoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String Login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {

        userService.login(memberLoginDto);
        return "";
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
