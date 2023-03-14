//package hello.hellospring.service;
//
//import hello.hellospring.domain.Member;
//import hello.hellospring.repository.MemberRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//import java.util.Optional;
//
//@Transactional
//public class MemberService {
//    private final MemberRepository memberRepository;
//
//    //DI dependency injection 의존성 주입
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    /**
//     * 회원가입
//     */
//    public Long join(Member member){
//        // 중복 회원x
//
//        /*
//        // null 일 가능성이 있을때 -> Optional
//        Optional<Member> result = memberRepository.findByName(member.getName());
//
//        ** result.orElseGet() : 값이 있으면 꺼내고 없으면 default 값 반환
//        result.ifPresent(m -> {
//        throw new IllegalStateException("이미 존재하는 회원입니다.");
//        */
//        validateDuplicateMember(member);  // 중복 회원 검증
//        memberRepository.save(member);
//        return member.getId();
//    }
//    //중복 회원검사 => 중복시 예외처리
//    private void validateDuplicateMember(Member member) {
//        memberRepository.findByName(member.getName())
//                .ifPresent(m->{
//                    throw  new IllegalStateException("이미 존재하는 회원입니다.");
//                });
//    }
//
//    /**
//     * 전체회원 조회
//     */
//    public List<Member> findMembers() {
//        return memberRepository.findAll();
//    }
//
//    public Optional<Member> findOne(Long memberId){
//        return memberRepository.findById(memberId);
//    }
//
//}
