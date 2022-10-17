package dev.instagram.web;

import dev.instagram.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/OAuth")
public class OAuthController {

    private final OAuthService oAuthService;
    private final MemberService memberService;

    // https://kauth.kakao.com/oauth/authorize?client_id=79f4f671542f5243fc95f3d12d3a3fc7&redirect_uri=http://localhost:8080/OAuth/kakao&response_type=code
    @GetMapping("/kakao")
    public void OauthKakao(@RequestParam String code) {
        System.out.println(code);

        String kakaoToken = oAuthService.getKakaoAccessToken(code);
        Map<String, String> mapInfo = oAuthService.createKakaoUser(kakaoToken);


    }
}
