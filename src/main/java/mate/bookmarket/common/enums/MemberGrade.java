package mate.bookmarket.common.enums;

import lombok.Getter;

import java.math.BigDecimal;

//기본생성자 및 생성자 설명할 수 있도록 공부하기
@Getter
public enum MemberGrade {
    BRONZE(new BigDecimal("0"), new BigDecimal("0")),
    SILVER(new BigDecimal("0.01"), new BigDecimal("0")),
    GOLD(new BigDecimal("0.03"), new BigDecimal("0")),
    PLATINUM(new BigDecimal("0.05"), new BigDecimal("0")),
    VIP(new BigDecimal("0.05"), new BigDecimal("0.05"));

    private final BigDecimal pointRate;
    private final BigDecimal discountRate;

    MemberGrade(BigDecimal pointRate, BigDecimal discountRate) {
        this.pointRate = pointRate;
        this.discountRate = discountRate;
    }

    //회원 등급에 따른 받을 포인트 금액
    public BigDecimal calculatePoint(BigDecimal price) {
        return price.multiply(pointRate);
    }

    //회원 등급에 따른 할인 금액
    public BigDecimal calculateDiscount(BigDecimal price) {
        return price.multiply(discountRate);
    }


}
