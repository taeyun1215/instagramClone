package dev.instagram.web;

import dev.instagram.domain.member.Member;
import dev.instagram.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {
    private final MemberService memberService;

    @Transactional
    public Member login(MemberLoginDto memberLoginDto) throws Exception {
        Member member = memberService.LoginMember(memberLoginDto);
        return member;
    }
}
