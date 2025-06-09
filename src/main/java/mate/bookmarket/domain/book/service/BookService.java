package mate.bookmarket.domain.book.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.bookmarket.domain.book.dto.request.RequestBookDTO;
import mate.bookmarket.domain.book.entity.BookEntity;
import mate.bookmarket.domain.book.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    //도서 등록
    @Transactional
    public boolean createBook(RequestBookDTO requestBookDTO) {
        BookEntity book = new BookEntity();
        book.createBook(requestBookDTO);
        bookRepository.save(book);
        return true;
    }

}
