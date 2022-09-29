package dev.instagram.web;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class MemberLoginDto {
    private String email;
    private String password;
}
