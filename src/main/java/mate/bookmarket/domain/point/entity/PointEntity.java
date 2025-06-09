package mate.bookmarket.domain.point.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mate.bookmarket.common.enums.PointPendingStatus;
import mate.bookmarket.domain.member.entity.MemberEntity;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "pointPending")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PointEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointPendingId;

    @ManyToOne(fetch = FetchType.LAZY) //기본은 즉시로딩, but 성능 때문에 지연로딩으로 변경
    @JoinColumn(name = "member_id") //변수명 + _id 가 기본이지만 명확하게 지정 하는게 좋음
    private MemberEntity member;

    //예상 적립 금액
    @Column
    private BigDecimal pointPendingAmount;

    //포인터 적립일
    @Column
    private LocalDate pointScheduledDate;

    @Column
    private Long orderId;

    // WAITING, COMPLETED, CANCELLED
    @Column
    @Enumerated(EnumType.STRING)
    private PointPendingStatus status;

    //정적 메서드랑 생성자랑 차이를 잘 모르겠음
    public static PointEntity createPendingPoint(MemberEntity member, BigDecimal pointPendingAmount, Long orderId) {
        PointEntity point = new PointEntity();
        point.member = member;
        point.pointPendingAmount = pointPendingAmount;
        //환불을 한 경우에는 변경하지 않도록 함
        point.pointScheduledDate = LocalDate.now().plusDays(3);
        point.orderId = orderId;
        point.status = PointPendingStatus.WAITING;

        return point;
    }

    public void changeStatus() {
        this.status = PointPendingStatus.CANCELLED;
    }
}
