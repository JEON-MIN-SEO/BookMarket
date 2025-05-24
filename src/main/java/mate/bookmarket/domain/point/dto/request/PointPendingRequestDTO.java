package mate.bookmarket.domain.point.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class PointPendingRequestDTO {

    private Long memberId;

    private BigDecimal booksAmount;

    private Long orderId;

}
