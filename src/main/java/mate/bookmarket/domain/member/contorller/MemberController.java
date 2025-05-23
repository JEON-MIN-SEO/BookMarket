package mate.bookmarket.domain.member.contorller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mate.bookmarket.domain.member.dto.MemberDTO;
//import mate.bookmarket.domain.member.dto.request.SignUpDTO;
import mate.bookmarket.domain.member.dto.request.MemberLogInDTO;
import mate.bookmarket.domain.member.dto.request.MemberSignUpDTO;
import mate.bookmarket.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller(View를 보여줄때) + @RequestBody(객체를 반환할때) = @RestController
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/signUp")
    public boolean signUp(@RequestBody MemberSignUpDTO memberSignUpDTO) {
        return memberService.signUp(memberSignUpDTO);
    }

    //로그인
    @PostMapping("/logIn")
    public boolean logIn(@RequestBody MemberLogInDTO memberLogInDTO) {
        return memberService.logIn(memberLogInDTO);
    }

    //아이디 찾기

    //비밀번호 찾기

    //로그아웃

    //회원탈퇴

}
