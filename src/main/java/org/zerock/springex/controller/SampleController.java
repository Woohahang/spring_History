package org.zerock.springex.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
//@RequestMapping("/todo")
public class SampleController {

    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public void hi() {
        log.info("\nhi");
    }

    // 겟 매핑은 링크를 하나만 잡을 수 있다. 받아올 때 겟으로 들어온다 명확하게 구분이 가능하다.
    @GetMapping("/bye")
    public void bye() {
        log.info("\nbye");
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public void list() {
        log.info("\nlist 메서드\n");
    }


    @GetMapping("/ex5")
    public String ex5(RedirectAttributes redirectAttributes) {

        // 리다이렉트할 때 키 벨류
        redirectAttributes.addAttribute("name", "hong");

        // 일회용 데이터 전달하고 삭제 값 유지
        redirectAttributes.addFlashAttribute("result", "nice");

        return "redirect:/todo/ex6";
    }

    @GetMapping("/ex6")
    public void ex6(RedirectAttributes redirectAttributes) {
        log.info("\nex6 이다.");
    }


    // 일부로 예외를 발생 시키는 상황
    // 스프링 MVC 컨트롤러의 특징은 메서드의 파라미터를 기본 자료형이나 객체 자료형을 마음대로 지정
    // 메소드의 리턴타입도 void String 객체 등 다양한 타입을 사용할 수 있다.
    @GetMapping("/ex7")
    public void ex7(String p1, int p2) {
        log.info("\nex7 메서드 p1 :  " + p1 + "\n");
        log.info("\nex7 메서드 p2 :  " + p2 + "\n");
    }


}
