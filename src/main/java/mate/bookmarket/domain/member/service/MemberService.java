package mate.bookmarket.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.bookmarket.domain.member.dto.request.MemberDeleteDTO;
import mate.bookmarket.domain.member.dto.request.MemberFindIdDTO;
import mate.bookmarket.domain.member.dto.request.MemberLogInDTO;
import mate.bookmarket.domain.member.dto.request.MemberSignUpDTO;
import mate.bookmarket.domain.member.dto.response.MemberReturnIdDTO;
import mate.bookmarket.domain.member.entity.MemberEntity;
import mate.bookmarket.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /// 회원가입시
    @Transactional
    public Boolean signUp(MemberSignUpDTO memberSignUpDTO) {

        extracted(memberSignUpDTO);
        extracted1(memberSignUpDTO);

        MemberEntity member = new MemberEntity();
        member.signUp(memberSignUpDTO);
        memberRepository.save(member);

        return true;
    }

    /// MemberSignUpDTO의 필드와 MemberLogInDTO의 필드가 같지만 파라미터가 다르면 매번 private void 메서드를 따로 만들어야 하나요? 재활용이 가능한가요?
    /// -> DTO를 인터페이스로 만들어서 받으면 재사용 가능
    private void extracted1(MemberSignUpDTO memberSignUpDTO) {
        if(memberRepository.existsByEmail(memberSignUpDTO.getEmail())){
            //회원탈퇴 한 이메일인지 체크
            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    private void extracted(MemberSignUpDTO memberSignUpDTO) {
        if(memberRepository.existsByUserId(memberSignUpDTO.getUserId())){
            //회원탈퇴 한 아이디인지 체크
            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }

    /// 로그인시 회원탈퇴인지 확인하기
    public boolean logIn(MemberLogInDTO memberLogInDTO) {

        //아이디가 있는지
        MemberEntity member = memberRepository.findByUserId(memberLogInDTO.getUserId());
        if (member == null) {
            throw new IllegalArgumentException("존재하지 않는 아이디 입니다.");
        }

        //계정이 정지 혹은 탈퇴하지 않았는지
        if (member.getIsWithdrawn()) {
            throw new IllegalArgumentException("회원탈퇴 아이디 입니다.");
        }

        if (member.getIsSuspended()) {
            throw new IllegalArgumentException("회원정지 아이디 입니다.");
        }

        //그 아이디에 비밀번호가 맞는지
        if (!member.getPassword().equals(memberLogInDTO.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        //true반환하기
        return true;
    }

//    private void isExistsUserId(MemberLogInDTO memberLogInDTO) {
//        if (memberRepository.existsByUserId(memberLogInDTO.getUserId())) {
//            throw new IllegalArgumentException("");
//        }
//    }
//
//    private void isRightPassword(MemberLogInDTO memberLogInDTO) {
//        if (memberRepository.findByUserId())
//    }

    //아이디 찾기
    @Transactional
    public MemberReturnIdDTO findUserId(MemberFindIdDTO memberFindIdDTO) {

        //이메일 빼내서 member테이블에서 해당 멤버 Entity 찾아오기
        String email = memberFindIdDTO.getEmail();
        //memberRepository.findByEmail(email) 반환값은 그냥 MemberEntity임
        MemberEntity member = memberRepository.findByEmail(email);
        //만약 해당 Email이 없다면 없음을 반환하기
        if (member == null) {
            //리텀 타입이 맞지 않음 Exception이랑
            //return new IllegalStateException("해당 이메일의 멤버가 존재하지 않습니다.");
            //throw는 그냥 던지는거
            throw new IllegalArgumentException("해당 이메일의 멤버가 존재하지 않습니다.");
        }

        //해당 userId를 찾아서 반환하기
        //MemberReturnIdDTO memberReturnIdDTO = new MemberReturnIdDTO(member.getUserId());

        return new MemberReturnIdDTO(member.getUserId());

    }

    /// 탈퇴시 아이디, 이메일 재사용 금지
    @Transactional
    public boolean deleteUser(MemberDeleteDTO memberDeleteDTO) {

        Long member = memberDeleteDTO.getMemberId();

        //회원을 DB에서 찾아내고 삭제하기 -> 그리고 true로 반환하기
        MemberEntity memberEntity = memberRepository.findById(member).orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다."));
        //orElseThrow는 Optional 안에 있는 Value를 반환 혹은 new Exception을 반환.
        memberEntity.deleteUser();

        return true;

    }

}



//
//    //회원탈퇴
//    @Transactional //save없이 Transactional을 달리면 가능 => 더티체킹 + 영속성
//    public boolean deleteUser(MemberDeleteDTO memberDeleteDTO) {
//
//        Long memberId = memberDeleteDTO.getMemberId();
//        //orElse == 깂이 없으면  == optional.empty
//        MemberEntity member = memberRepository.findById(memberId).
//                orElseThrow(() -> new IllegalArgumentException("회원이 존재하지 않습니다.") );
//
//        member.deleteUser();
//
//        return true;
//    }
