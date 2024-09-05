package Jungwoo.HelloSpring.service;

import Jungwoo.HelloSpring.domain.Member;
import Jungwoo.HelloSpring.repository.MemberRepository;
import Jungwoo.HelloSpring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;


    @Test
    void join() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    void duplicate_member_exception() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        /*
         * join이 exception을 던질 것으로 예상하고 실제로 발생하는지 검증
         *  */
        memberService.join(member1);
        // assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*
        try {
            memberService.join(member2);
            fail(); // 그대로 join해버리면 테스트가 제대로 되지 않은 것이므로
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/


        //then

    }

}