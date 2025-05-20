package mate.bookmarket.domain.point.dto;

import mate.bookmarket.common.enums.PointPendingStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PointPendingDTO {

    private Long pointPendingId;

    private Long memberId;

    //예상 적립 금액
    private BigDecimal pointPendingAmount;

    //포인터 적립일
    private LocalDateTime pointScheduledDate;

    private Long orderId;

    // WAITING, COMPLETED, CANCELLED
    private PointPendingStatus status;

}
