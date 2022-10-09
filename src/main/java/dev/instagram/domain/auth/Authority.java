package dev.instagram.domain.auth;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
@Builder
public class Authority {

    @Id
    @Column(name = "authority_name")
    private String authorityName;
}
