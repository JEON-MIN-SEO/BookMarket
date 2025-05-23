package mate.bookmarket.domain.member.repository;

import mate.bookmarket.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByUserId(String userId);

    MemberEntity findByUserId(String userId);
}
