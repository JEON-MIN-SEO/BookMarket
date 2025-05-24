package mate.bookmarket.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mate.bookmarket.common.enums.MemberGrade;
import mate.bookmarket.domain.member.dto.request.MemberSignUpDTO;

import java.math.BigDecimal;

@Entity
@Table(name = "Member")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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
    private BigDecimal point = new BigDecimal("0");

    @Column
    @Enumerated(EnumType.STRING) //배열로 나오는게 아니라 String으로 출력됨
    private MemberGrade myGrade = MemberGrade.BRONZE;

    @Column
    private Boolean isSuspended = false;

    @Column
    private Boolean isWithdrawn = false;

    public void signUp(MemberSignUpDTO memberSignUpDTO) {
        this.userId = memberSignUpDTO.getUserId();
        this.password = memberSignUpDTO.getPassword();
        this.email = memberSignUpDTO.getEmail();
        this.name = memberSignUpDTO.getName();
//        this.myGrade = MemberGrade.BRONZE;
//        this.point = new BigDecimal("0");
//        this.isSuspended = false;
//        this.isWithdrawn = false;
    }
}
