package dev.instagram.web;

import dev.instagram.domain.member.Member;
import dev.instagram.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class SingupService {
    private final MemberService memberService;

    @Transactional
    public Member signup(MemberSignupDto memberSignupDto) throws Exception {
        Member member = memberSignupDto.toEntity();
        Member memberEntity = memberService.registerMember(member);

        return memberEntity;
    }

}