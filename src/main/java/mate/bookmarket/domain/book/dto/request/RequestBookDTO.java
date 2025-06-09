package mate.bookmarket.domain.book.dto.request;

import lombok.Getter;
import mate.bookmarket.common.enums.BookStatus;

@Getter
public class RequestBookDTO {

    private String isbn;

    private String bookImg;

    private BookStatus bookStatus;

}
