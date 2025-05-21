package mate.bookmarket.domain.member.dto;

import mate.bookmarket.common.enums.MemberGrade;

import java.math.BigDecimal;

public class MemberDTO {

    private Long memberId;

    private String userId;

    private String password;

    private String email;

    //중복여부 정책 정하기
    private String name;

    private BigDecimal point;

    private MemberGrade myGrade;

    //회원정지 여부
    private Boolean isSuspended;

    //회원탈퇴 여부
    private Boolean isWithdrawn;

}
