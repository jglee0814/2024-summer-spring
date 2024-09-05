package Jungwoo.HelloSpring.service;

import Jungwoo.HelloSpring.domain.Member;
import Jungwoo.HelloSpring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;


public class MemberService {

    private final MemberRepository memberRepository;


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public Long join(Member member){

        ValidateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();


    }

    private void ValidateDuplicateMember(Member member) { // ctrl + Alt + M: extract method
        memberRepository.findByName(member.getName())
                        .ifPresent( // 값이 존재하면 값 전달
                                m -> { // lambda expression
                                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                                });
    }
    /*
    * 전체 회원 존재
    * */

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
