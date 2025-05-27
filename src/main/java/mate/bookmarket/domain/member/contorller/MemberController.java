package mate.bookmarket.domain.member.contorller;

import lombok.RequiredArgsConstructor;
import mate.bookmarket.domain.member.dto.request.MemberDeleteDTO;
import mate.bookmarket.domain.member.dto.request.MemberFindIdDTO;
import mate.bookmarket.domain.member.dto.request.MemberLogInDTO;
import mate.bookmarket.domain.member.dto.request.MemberSignUpDTO;
import mate.bookmarket.domain.member.dto.response.MemberReturnIdDTO;
import mate.bookmarket.domain.member.service.MemberService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Controller(View를 보여줄때) + @ResponseBody(객체를 반환할때) = @RestController
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
    @PostMapping("/findId")
    public MemberReturnIdDTO findUserId(@RequestBody MemberFindIdDTO memberFindIdDTO) {

        //json으로 반환하기 위해서는 @Getter를 기반으로 반환하기 때문에 DTO를 반환하기 위해서는 @Getter가 있어야함.
        return memberService.findUserId(memberFindIdDTO);

    }

    //비밀번호 변경 -> 비밀번호 암호화

    //로그아웃


    //회원탈퇴
    @PostMapping("/delete")
    //RequestBody는 json을 java 객체로 혹은 기본형 같은 입력값을 자바 객체로 바꿔주는 어노테이션
    public boolean deleteUser(@RequestBody MemberDeleteDTO memberDeleteDTO) {

        return memberService.deleteUser(memberDeleteDTO);

    }
    //회원탈퇴 할 사용자 찾을때 PK값으로
    //빼낸 회원 정보에서 Withdrawn을 true로 변경
    //회원탈퇴 시간 함께 기록하기
    //반환은
}
