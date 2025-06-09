package mate.bookmarket.domain.book.repository;

import mate.bookmarket.domain.book.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
}
