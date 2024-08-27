package Jungwoo.HelloSpring.repository;

import Jungwoo.HelloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // member 객체를 저장소에 저장
    Optional<Member> findById(Long id); // list에서 null poineter exception을 방지
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
