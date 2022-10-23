package dev.instagram.domain.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);
    Member findByMemberId(String memberId);
    boolean existsByMemberId(String memberId);

    Optional<Member> findOneWithAuthoritiesByMemberId(String memberId);
}
