package mate.bookmarket.domain.member.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class MemberLogInDTO {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;

}
