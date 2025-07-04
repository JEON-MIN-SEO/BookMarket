package mate.bookmarket.domain.book.controller;

import lombok.RequiredArgsConstructor;
import mate.bookmarket.domain.book.dto.request.RequestBookDTO;
import mate.bookmarket.domain.book.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book") //알아보기
@RequiredArgsConstructor //알아보기
public class BookController {

    private final BookService bookService;

    //테스트용 -> 데이터 넣기
    @PostMapping("/plus")
    public boolean makeBook(@RequestBody RequestBookDTO requestBookDTO) {
        return bookService.createBook(requestBookDTO);
    }

    //도서 검색

    //도서 상세정보

    //도서 등록

}
