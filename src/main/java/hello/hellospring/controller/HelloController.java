package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller//이 클래스가 스프링의 컨트롤러임을 나타냄
public class HelloController {
    @GetMapping("hello")// 특정 URL에 대한 GET 요청을 처리하는 메서드
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");

        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")//http://localhost:8080/hello-string?name=spring
    @ResponseBody //http body에 데이터를 직접넣어줌
    public String helloString(@RequestParam("name") String name) {// @RequestParam 요청 파라미터를 메서드의 매개변수로 바인딩
        return "hello " + name;//"hello spring" 소스코드없이그대로 내려감
    }
    @GetMapping("hello-api")//http://localhost:8080/hello-api?name=spring
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name){
        Hello hello = new Hello();
        hello.setName(name);//파라미터로 넘어온 name
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}