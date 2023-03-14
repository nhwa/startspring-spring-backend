package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //jpa는 EntityManager를 통해 모든 것이 동작
    //jpa는 EntityManager를 자동으로 생성하고 내부적으로 db와 연결시켜준다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        //persist 영구적으로 저장.
        em.persist(member);
        return member;
    }

    @Override
    //pk(private) 기반은 sql작성 필요X
    public Optional<Member> findById(long id) {
        Member member = em.find(Member.class,id);
        return Optional.ofNullable(member);
    }

    //pk(private) 기반이 아닌 것들은 jpql을 작성해야한다.
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll(){
        return em.createQuery("select m from Member m",Member.class)
                .getResultList();
    }
}
