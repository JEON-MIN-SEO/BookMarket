package mate.bookmarket.domain.member.repository;

import mate.bookmarket.domain.member.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<MemberEntity, Long> {

    boolean existsByEmail(String email);

    boolean existsByUserId(String userId);

    MemberEntity findByUserId(String userId);

    //findById랑 같은거임 => Entity의 @Id를 기준으로 찾는거임
    MemberEntity findByMemberId(Long memberId);

    @Query("SELECT m FROM MemberEntity m WHERE m.memberId = :memberId")
    MemberEntity customMemberId(@Param("memberId")Long memberId);

    MemberEntity findByEmail(String email);
}
