package dev.instagram.web;

import dev.instagram.domain.member.Member;
import dev.instagram.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final MemberService memberService;

    @Transactional
    public Member signup(MemberSignupDto memberSignupDto) throws Exception {
        Member memberEntity = memberService.registerMember(memberSignupDto);
        return memberEntity;
    }

    @Transactional
    public Member login(MemberLoginDto memberLoginDto) throws Exception {
        Member member = memberService.LoginMember(memberLoginDto);
        return member;
    }

}