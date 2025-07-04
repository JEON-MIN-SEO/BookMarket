package mate.bookmarket.domain.point.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PointPendingRequestDTO {

    private Long memberId;

    private BigDecimal booksAmount;

    private Long orderId;

}
