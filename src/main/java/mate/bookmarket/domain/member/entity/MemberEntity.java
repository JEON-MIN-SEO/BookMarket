package mate.bookmarket.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mate.bookmarket.common.enums.MemberGrade;

import java.math.BigDecimal;

@Entity
@Table(name = "Member")
@Getter
public class MemberEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column
    private String userId;

    @Column
    private String password;

    @Column
    private String email;

    @Column
    private String name;

    @Column
    private BigDecimal point;

    @Column
    private MemberGrade myGrade;

    @Column
    private Boolean isSuspended;

    @Column
    private Boolean isWithdrawn;
}
