package mate.bookmarket.domain.point.controller;

import lombok.RequiredArgsConstructor;
import mate.bookmarket.domain.point.dto.request.PointPendingRequestDTO;
import mate.bookmarket.domain.point.service.PointService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/point")
public class PointController {

    private final PointService pointService;

    //테스트 - 포인트 팬딩 컨트롤러
    @PostMapping("/pending")
    public BigDecimal pendingPoint(@RequestBody PointPendingRequestDTO requestDTO) {
        return pointService.pendingPoint(requestDTO);
    }

}
