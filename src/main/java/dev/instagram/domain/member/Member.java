package dev.instagram.domain.member;

import dev.instagram.domain.auth.Authority;
import lombok.*;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@DynamicUpdate // 특정 컬럼만 업데이트 해주기 위함.
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    private String memberId;
    private String password;
    private String email;
    private String phoneNumber;
    private String username;

    @Column(name = "auth_email")
    private String authEmail;

    @Column(name = "activated")
    private boolean activated;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(
            name = "user_authority",
            joinColumns = {@JoinColumn(name = "id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "authority_name")}
    )
    private Set<Authority> authorities;

}
