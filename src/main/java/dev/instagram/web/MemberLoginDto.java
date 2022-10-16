package dev.instagram.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class MemberLoginDto {
    private String memberId;
    private String password;
}
