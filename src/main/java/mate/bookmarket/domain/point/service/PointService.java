package mate.bookmarket.domain.point.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.bookmarket.common.enums.MemberGrade;
import mate.bookmarket.domain.member.entity.MemberEntity;
import mate.bookmarket.domain.member.repository.MemberRepository;
import mate.bookmarket.domain.point.dto.request.PointPendingRequestDTO;
import mate.bookmarket.domain.point.dto.request.RePointPendingRequestDTO;
import mate.bookmarket.domain.point.entity.PointEntity;
import mate.bookmarket.domain.point.repositroy.PointRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class PointService {

    private final MemberRepository memberRepository;
    private final PointRepository pointRepository;

    //포인트 적립 대기 (팬딩)
    //구매 -> 포인트 적립을 요청
    //적립 예상금 계산
    //적립
    @Transactional
    public BigDecimal pendingPoint(PointPendingRequestDTO requestDTO) {

        //회원 찾기
        MemberEntity member = memberRepository.findByMemberId(requestDTO.getMemberId());
        //오류에 관해서 찾아보기
        if (member == null) {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }
        //회원 등급
        MemberGrade memberGrade = member.getMyGrade();

        //회원 등급에 따른 포인트 적용률
        BigDecimal expectPointAmount = memberGrade.calculatePoint(requestDTO.getBooksAmount());

        //회원 등급에 따른 할인률
        BigDecimal expectDiscountRate = memberGrade.calculateDiscount(requestDTO.getBooksAmount());

        //PointEntity 생성 (생성자 or 정적 메서드 사용)
        PointEntity pointEntity = PointEntity.createPendingPoint(member, expectPointAmount, requestDTO.getOrderId());

        //포인트 적용
        pointRepository.save(pointEntity);

        //회원 등급에 따른 할인률 반환
        return expectDiscountRate;

    }

    //포인트 적립 취소 (환불)
    //3개의 도서 중 1개만 환불한 경우 등등
    /// 주문당 포인트 적립이 아닌 도서 별 포인트 적립으로 변경
    @Transactional
    public BigDecimal cancelPending(RePointPendingRequestDTO pendingRequestDTO) {
        //어떤 주문이 환불인지 확인
        /// 해당 포인트 팬딩 상태를 취소로 변경
        PointEntity point = pointRepository.findByOrderId(pendingRequestDTO.getOrderId());
        if (point == null) {
            throw new IllegalArgumentException("해당 주문이 존재하지 않습니다.");
        }

        //상태를 취소로 변경
        point.changeStatus();

        //위에있는 pendingPoint 메서드를 사용하기 위해 DTO를 만들기
        /// 멤버ID, 가격, 주문ID
        PointPendingRequestDTO pointPendingRequestDTO
                = new PointPendingRequestDTO(point.getMember().getMemberId(), pendingRequestDTO.getBooksAmount(), pendingRequestDTO.getOrderId());

        //변동된 총 가격으로 계산 후 적립
        /// 같은 클래스 메인 메서드를 불러서 사용하는건 좋지 않음
        return pendingPoint(pointPendingRequestDTO);

    }

    //포인트 적립 확정 (스케줄러 기반)
    //매일 0시에 체크
    //적립 예정일 당일 된 경우 지급으로 상태 변경 후 히스토리에 기록 요청


    //포인트 적립 (히스토리)
    //포인트 적립 후 Member에서 point 업데이트

    //포인트 사용 (히스토리)
    //포인트 사용 후 Member에서 point 업데이트


    //포인트 내역 관리 (멤버별 조회)
    //MemberId를 통해 해당 사용자의 포인트 내역 조회

    //포인트 내역 관리 (모든 사용자 조회)
    //MemberId를 통해 해당 사용자의 포인트 내역 조회

}
