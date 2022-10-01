package dev.instagram.domain.member;

import dev.instagram.web.MemberLoginDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Transactional
    public Member registerMember(Member member) throws Exception {
        Member validateMember = validateDuplicatedMember(member);
        memberRepository.save(validateMember);
        return validateMember;
    }

    public Member validateDuplicatedMember(Member member) throws Exception {
        boolean checkEmail = memberRepository.existsByEmail(member.getEmail());
        if (checkEmail) {
            throw new Exception("중복된 아이디입니다.");
        }
        return member;
    }

    @Transactional
    public Member LoginMember(MemberLoginDto memberLoginDto) throws Exception {
        Member member = validateLoginMember(memberLoginDto);
        return member;
    }

    public Member validateLoginMember(MemberLoginDto memberLoginDto) throws Exception {
        Member member = memberRepository.findByEmail(memberLoginDto.getEmail());
        if (member == null) {
            throw new Exception("해당하는 아이디가 없습니다.");
        }
        if (!bCryptPasswordEncoder.matches(memberLoginDto.getPassword(), member.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }
}
