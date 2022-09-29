package dev.instagram.domain.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Member registerMember(Member member) throws Exception {
        Member validateMember = validateMember(member);
        String rawPassword = validateMember.getPassword();
        String bCryptPassword = bCryptPasswordEncoder.encode(rawPassword);
        validateMember.setPassword(bCryptPassword);

        memberRepository.save(validateMember);
        return validateMember;
    }

    public Member validateMember(Member member) throws Exception {
        boolean checkEmail = memberRepository.existsByEmail(member.getEmail());
        if (checkEmail) {
            throw new Exception("중복된 아이디 입니다.");
        }
        return member;
    }

}
