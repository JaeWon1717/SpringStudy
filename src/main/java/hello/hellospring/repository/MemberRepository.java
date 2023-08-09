package hello.hellospring.repository;

import hello.hellospring.damain.Member;
import java.util.List;
import java.util.Optional;
public interface MemberRepository {
    Member save(Member member);//회원 저장소에 저장
    Optional<Member> findById(Long id);//아이디 찾아오기
    Optional<Member> findByName(String name);
    List<Member>findAll();//모든회원 반환
}
