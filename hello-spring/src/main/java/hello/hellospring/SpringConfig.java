//package hello.hellospring;
//
//import hello.hellospring.aop.TimeTraceAop;
//import hello.hellospring.repository.MemberRepository;
//import hello.hellospring.service.MemberService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.sql.Time;
//
//@Configuration
//public class SpringConfig {
//
//    private final MemberRepository memberRepository;
//
//    @Autowired
//    public SpringConfig(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//
//    @Bean
//    public MemberService memberService(){
//        return new MemberService(memberRepository);
//    }
//
////    @Bean
////    public TimeTraceAop TimeTraceAop(){
////        return new TimeTraceAop();
////    }
//
////    private EntityManager em;
////    @Autowired
////    public SpringConfig(EntityManager em) {
////        this.em = em;
////    }
//
////    private DataSource dataSource;
////
////    @Autowired
////    public SpringConfig(DataSource dataSource) {
////        this.dataSource = dataSource;
////    }
//
////    @Bean
////    public MemberService memberService(){
////        return new MemberService(memberRepository());
////    }
//
////    @Bean
////    public MemberRepository memberRepository(){
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource);
////        return new JdbcTemplateMemberRepository(dataSource);
////        return new JpaMemberRepository(em);
////    }
//
//
//}
