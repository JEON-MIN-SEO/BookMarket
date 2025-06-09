package mate.bookmarket.domain.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mate.bookmarket.common.enums.BookStatus;
import mate.bookmarket.domain.book.dto.request.RequestBookDTO;

@Entity
//@AllArgsConstructor //직랼화 역직렬화때 사용됨 왠만하면 사용하는게 좋음
@NoArgsConstructor
@Getter
//@Setter는 외부에서 변경하는 거임
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //생성하다
    /// IDENTITY는 각 DB마다의 PK생성 방식을 따름
    private Long bookId;

    private String isbn;

    private String bookImg;

    private BookStatus bookStatus;

    //private로 선언했고 Setter나 생성자를 통해 해당 필드를 수정할 수 없기 때문에 final을 추천하고 있음
    private boolean isSold = false;

    public void createBook(RequestBookDTO requestBookDTO) {
        this.isbn = requestBookDTO.getIsbn();
        this.bookImg = requestBookDTO.getBookImg();
        this.bookStatus = requestBookDTO.getBookStatus();
    }
}
