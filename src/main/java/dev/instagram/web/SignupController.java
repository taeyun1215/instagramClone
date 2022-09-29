package dev.instagram.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Objects;

@RequiredArgsConstructor
@Controller
public class SignupController {
    private final SingupService singUpService;

    @GetMapping("/signup")
    public String SignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public String Signup(@RequestBody MemberSignupDto memberSignUpDto) throws Exception {
        if (!Objects.equals(memberSignUpDto.getPassword1(), memberSignUpDto.getPassword2())) {
            throw new Exception("비밀번호와 비밀번호 확인이 같지 않습니다.");
        }

        singUpService.signup(memberSignUpDto);

        return "login";
    }

}
