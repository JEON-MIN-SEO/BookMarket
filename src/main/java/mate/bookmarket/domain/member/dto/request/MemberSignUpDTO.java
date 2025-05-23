package mate.bookmarket.domain.member.dto.request;

import lombok.Getter;

@Getter
public class MemberSignUpDTO {

    private String userId;

    private String password;

    private String email;

    private String name;

}
