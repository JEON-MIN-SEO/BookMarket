package mate.bookmarket.domain.point.dto.request;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class RePointPendingRequestDTO {

    private Long orderId;

    private BigDecimal booksAmount;

}
