package hello.hellospring.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    //API 방식 => JSON 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }
    static class Hello{
        private  String name;

        //Property 접근 방식
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }

    //view가 필요없음
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    // = getPost
    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data","spring!!");
        model.addAttribute("data2","두번째야");
        // 'resources:templates /' +{ ViewName } + '.html'
        return "hello";
    }

}
