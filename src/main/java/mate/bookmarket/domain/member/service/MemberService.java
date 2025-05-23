package mate.bookmarket.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mate.bookmarket.domain.member.dto.request.MemberLogInDTO;
import mate.bookmarket.domain.member.dto.request.MemberSignUpDTO;
import mate.bookmarket.domain.member.entity.MemberEntity;
import mate.bookmarket.domain.member.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Boolean signUp(MemberSignUpDTO memberSignUpDTO) {

        extracted(memberSignUpDTO);
        extracted1(memberSignUpDTO);

        MemberEntity member = new MemberEntity();
        member.signUpEntity(memberSignUpDTO);
        memberRepository.save(member);

        return true;
    }

    private void extracted1(MemberSignUpDTO memberSignUpDTO) {
        if(memberRepository.existsByEmail(memberSignUpDTO.getEmail())){

            throw new IllegalStateException("이미 존재하는 이메일 입니다.");
        }
    }

    private void extracted(MemberSignUpDTO memberSignUpDTO) {
        if(memberRepository.existsByUserId(memberSignUpDTO.getUserId())){

            throw new IllegalStateException("이미 존재하는 아이디 입니다.");
        }
    }

    public boolean logIn(MemberLogInDTO memberLogInDTO) {

        MemberEntity member = new MemberEntity();
        //0인지는 DTO에서 체크
        //아이디가 있는지 체크 있다면 정보 가져오기
        if (memberRepository.existsByUserId(memberLogInDTO.getUserId())) {
            member = memberRepository.findByUserId(memberLogInDTO.getUserId());
//            password = memberRepository.findByUserId(memberLogInDTO.getUserId()).getPassword();
        } else {
            return false;
        }
        //해당 아이디에 맞는 비밀번호인지 체크
        if (!member.getPassword().equals(memberLogInDTO.getPassword())) {
            return false;
        }

        return true;
    }
}
