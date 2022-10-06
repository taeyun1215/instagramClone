package dev.instagram.domain.member;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Setter
@Getter
public class Authority {

    @Id
    private String authorityName;
}
