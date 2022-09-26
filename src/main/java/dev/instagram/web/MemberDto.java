package dev.instagram.web;

import dev.instagram.domain.member.Member;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class MemberDto {
    private String email;
    private String password;
    private String phoneNumber;
    private String username;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .phoneNumber(phoneNumber)
                .username(username)
                .build();
    }
}
