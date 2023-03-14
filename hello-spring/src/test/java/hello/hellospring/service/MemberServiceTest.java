package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    //실행되기 전
    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    //테스트가 실행되고 난후
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    //테스트는 한글로도 많이 적음
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hello");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given 상황이 주어지고
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when 실행했을때
        memberService.join(member1);
        // assertThrows(예외,() -> 메소드 ) => 메소드를 실행하였을때 같은 예외가 발생하였는지 확인
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

/*
        try {
            memberService.join(member2);
            fail("예외가 발생해야합니다.");
        } catch (IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.1234");
        }
*/

        //then 결과가 나와야함
    }

    @Test
    void findMembers() {

    }

    @Test
    void findOne() {
    }
}