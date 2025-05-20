package mate.bookmarket.domain.point.dto;

import mate.bookmarket.common.enums.PointHistoryStatus;

import java.math.BigDecimal;

public class PointHistoryDTO {

    private Long pointHistoryId;
    
    private Long memberId;

    //포인트 금액
    private BigDecimal pointAmount;

    private Long orderId;

    // EARN, USE, REFUND
    private PointHistoryStatus status;

}
