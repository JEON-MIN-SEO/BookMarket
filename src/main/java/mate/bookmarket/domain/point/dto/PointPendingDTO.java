package mate.bookmarket.domain.point.dto;

import mate.bookmarket.common.enums.PointPendingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//팬딩이랑 히스토리 연결해야함
//무결성이 가장 중요
public class PointPendingDTO {

    private Long pointPendingId;

    //객체 넣는게 좋은지 FK만 받는게 좋을지
    private Long memberId;

    //예상 적립 금액
    private BigDecimal pointPendingAmount;

    //포인터 적립일
    private LocalDateTime pointScheduledDate;

    private Long orderId;

    // WAITING, COMPLETED, CANCELLED
    private PointPendingStatus status;

}
