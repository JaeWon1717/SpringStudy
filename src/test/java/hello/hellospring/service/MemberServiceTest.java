package hello.hellospring.service;

import hello.hellospring.damain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;// =new MemberService();
    MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    @BeforeEach
    public void beforeEach()
    {
        memberRepository =new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }


    @AfterEach
    public void afterEach(){//하나의 테스트 케이스들이 끝날때마다 저장소를 비워줘야함
        memberRepository.clearStore(); //메모리클리어
    }
    @Test
    void 회원가입() {//회원 가입이 잘 되는지 보기
        //given ->뭔가가 주어 졌는데 ★
        Member member =new Member();
        member.setName("hello memberservice");

        //when -> 이걸 실행 했을때 ★
        Long saveId =memberService.join(member);


        //then -> 결과가 이게 나와야 해 ★

        //Optional<Member> one =memberService.findOne(saveId).get;
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }
    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 =new Member();
        member1.setName("spring");

        Member member2 =new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,()->memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는회원입니다.");
        /*
        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는회원입니다.");
        }
*/
        //then
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}