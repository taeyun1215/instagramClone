package dev.instagram.web;

import dev.instagram.domain.auth.Authority;
import dev.instagram.domain.member.Member;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class MemberSignupDto {
    private String memberId;
    private String password1;
    private String password2;
    private String email;
    private String phoneNumber;
    private String username;

    public Member toEntity(PasswordEncoder passwordEncoder, Authority authority) {
        return Member.builder()
                .memberId(memberId)
                .password(passwordEncoder.encode(password1))
                .email(email)
                .phoneNumber(phoneNumber)
                .username(username)
                .activated(true)
                .authorities(Collections.singleton(authority))
                .build();
    }
}
