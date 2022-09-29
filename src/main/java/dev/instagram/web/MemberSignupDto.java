package dev.instagram.web;

import dev.instagram.domain.member.Member;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class MemberSignupDto {
    private String email;
    private String password1;
    private String password2;
    private String phoneNumber;
    private String username;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password1)
                .phoneNumber(phoneNumber)
                .username(username)
                .build();
    }
}
