package hello.hellospring.controller;
import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

//@Controller => @Controller라는 아노테이션을 보고 자동으로 스프링 컨테이너에서 관리 해줌 (스프링 빈 관리)
@Controller
public class MemberController {

    // 여러개의 인스턴스를 생성할 필요가 없음
    //private final MemberService memberService = new MemberService();

    // spring Container에 등록
    private final MemberService memberService;

    /* 생성자에 @Autowired 가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.
       DI dependency injection 의존성 주입
     */
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //url 매핑(조회할때)
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }
    //폼 전달(데이터 전송)
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }

}
