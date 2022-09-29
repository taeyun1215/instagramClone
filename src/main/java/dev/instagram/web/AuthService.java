package dev.instagram.web;

import dev.instagram.domain.member.Member;
import dev.instagram.domain.member.MemberRepository;
import dev.instagram.domain.member.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final MemberService memberService;

    @Transactional
    public Member signup(MemberDto memberDto) throws Exception {
        Member member = memberDto.toEntity();
        Member memberEntity = memberService.registerMember(member);

        return memberEntity;
    }

}