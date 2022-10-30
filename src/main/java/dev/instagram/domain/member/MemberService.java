package dev.instagram.domain.member;

import dev.instagram.domain.auth.Authority;
import dev.instagram.util.SecurityUtil;
import dev.instagram.web.MemberLoginDto;
import dev.instagram.web.MemberSignupDto;
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
    public Member registerMember(MemberSignupDto memberSignupDto) throws Exception {
        MemberSignupDto validateMember = validateDuplicatedMember(memberSignupDto);

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = validateMember.toEntity(bCryptPasswordEncoder, authority);
        memberRepository.save(member);

        return member;
    }

    public MemberSignupDto validateDuplicatedMember(MemberSignupDto memberSignupDto) throws Exception {
        boolean checkId = memberRepository.existsByMemberId(memberSignupDto.getMemberId());
        if (checkId) {
            throw new Exception("중복된 아이디입니다.");
        }
        return memberSignupDto;
    }

    @Transactional
    public Member LoginMember(MemberLoginDto memberLoginDto) throws Exception {
        Member member = validateLoginMember(memberLoginDto);
        return member;
    }

    public Member validateLoginMember(MemberLoginDto memberLoginDto) throws Exception {
        Member member = memberRepository.findByMemberId(memberLoginDto.getMemberId());
        if (member == null) {
            throw new Exception("해당하는 아이디가 없습니다.");
        }
        if (!bCryptPasswordEncoder.matches(memberLoginDto.getPassword(), member.getPassword())) {
            throw new Exception("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMemberWithAuthorities() {
        return SecurityUtil.getCurrentUsername().flatMap(memberRepository::findOneWithAuthoritiesByMemberId);
    }

    @Transactional(readOnly = true)
    public Optional<Member> getMemberWithAuthorities(String memberId) {
        return memberRepository.findOneWithAuthoritiesByMemberId(memberId);
    }

    @Transactional(readOnly = true)
    public Member saveAuthEmail(String authCode, String email) {
        Member findMember = memberRepository.findByEmail(email);
        findMember.setAuthEmail(authCode);

        return memberRepository.save(findMember);
    }

    @Transactional(readOnly = true)
    public Member findId(String authCode, String memberEmail) throws Exception {
        Member findMember = memberRepository.findByEmailandAuthEmail(authCode, memberEmail);

        if (findMember == null) {
            throw new Exception("이메일 인증번호를 틀렸습니다.");
        }

        return findMember;
    }


}
