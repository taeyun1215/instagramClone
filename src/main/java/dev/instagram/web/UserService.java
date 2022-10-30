package dev.instagram.web;

import dev.instagram.domain.member.Member;
import dev.instagram.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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

    @Transactional
    public Optional<Member> getMemberWithAuthorities() {
        Optional<Member> member = memberService.getMemberWithAuthorities();
        return member;
    }

    @Transactional
    public Optional<Member> getMemberWithAuthorities(String email) {
        Optional<Member> member = memberService.getMemberWithAuthorities(email);
        return member;
    }

    @Transactional
    public Member saveAuthEmail(String authCode, String email) {
        Member member = memberService.saveAuthEmail(authCode, email);
        return member;
    }

    @Transactional
    public String findId(String authCode, String memberEmail) throws Exception{
        Member member = memberService.findId(authCode, memberEmail);
        return member.getEmail();
    }
}