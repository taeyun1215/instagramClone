package dev.instagram.web;

import dev.instagram.domain.member.Member;
import dev.instagram.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Member signup(Member member) {
        String rawPassword = member.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(rawPassword);
        member.setPassword(encPassword);

        Member memberEntity = memberRepository.save(member);

        return memberEntity;
    }

    @Transactional
    public Member userUpdate(Member member) {
        Member memberEntity = memberRepository.save(member);
        return memberEntity;
    }
}