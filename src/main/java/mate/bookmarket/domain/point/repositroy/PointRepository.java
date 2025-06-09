package mate.bookmarket.domain.point.repositroy;

import mate.bookmarket.domain.point.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity, Long> {

    public PointEntity findByOrderId(Long orderId);
}
