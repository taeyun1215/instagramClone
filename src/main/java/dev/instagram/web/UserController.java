package dev.instagram.web;

import dev.instagram.JWT.JwtTokenDto;
import dev.instagram.JWT.JwtTokenProvider;
import dev.instagram.domain.member.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.nurigo.java_sdk.exceptions.CoolsmsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/api")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final EmailService emailService;
    private final PhoneNumberCheckService phoneNumberCheckService;

    @GetMapping("/login")
    public ResponseEntity<?> LoginForm() {
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenDto> Login(@RequestBody MemberLoginDto memberLoginDto) throws Exception {

//        Member loginMember = userService.login(memberLoginDto);

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(memberLoginDto.getMemberId(), memberLoginDto.getPassword());

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtTokenProvider.createToken(authentication);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Bearer " + jwt);

        return new ResponseEntity<>(new JwtTokenDto(jwt), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/signup")
    public String SignupForm() {
        return "signup";
    }

    @PostMapping("/signup")
    public ResponseEntity<Member> Signup(@RequestBody MemberSignupDto memberSignUpDto) throws Exception {
        if (!Objects.equals(memberSignUpDto.getPassword1(), memberSignUpDto.getPassword2())) {
            throw new Exception("??????????????? ???????????? ????????? ?????? ????????????.");
        }
        return ResponseEntity.ok(userService.signup(memberSignUpDto));
    }

    @GetMapping("/member")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Optional<Member>> getMyUserInfo(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getMemberWithAuthorities());
    }

    @GetMapping("/member/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Optional<Member>> getUserInfo(@PathVariable String email) {
        return ResponseEntity.ok(userService.getMemberWithAuthorities(email));
    }

    @PostMapping("forget/id")
    public ResponseEntity<Member> forgetId(@RequestBody EmailRequestDto emailDto) throws Exception, MessagingException, UnsupportedEncodingException {
        String authCode = emailService.sendEmail(emailDto.getEmail());
        return ResponseEntity.ok(userService.saveAuthEmail(authCode, emailDto.getEmail()));
    }

    @PostMapping("forget/idConfirm")
    public ResponseEntity<String> forgetIdConfirm(@RequestBody String authCode, @RequestBody String memberEmail) throws Exception{
        return ResponseEntity.ok(userService.findId(authCode, memberEmail));
    }

    @PostMapping("forget/password")
    public String forgetPassword(@RequestBody ) {

    }

    @PostMapping("forget/passwordConfirm")
    public String forgetPasswordConfirm(@RequestBody ) {

    }

    // coolSMS ?????? ?????? ??????
    @GetMapping("/check/SMS")
    public @ResponseBody String sendSMS(@RequestBody String to) throws CoolsmsException {
        return phoneNumberCheckService.PhoneNumberCheck(to);
    }

}
