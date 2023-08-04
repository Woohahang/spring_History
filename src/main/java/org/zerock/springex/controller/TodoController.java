package org.zerock.springex.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.springex.dto.PageRequestDTO;
import org.zerock.springex.dto.TodoDTO;
import org.zerock.springex.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todo")
@Log4j2
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;


//    @RequestMapping(value = "/list", method = RequestMethod.GET)
//    public void list(Model model) {
//        log.info("\nlist 메서드\n");
//
//        model.addAttribute("dtoList", todoService.getAll());
//
//    }


    // @Valid 란, 데이터를 검증하는 기능 ( 유효성 검사 )
    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {

        log.info("\n/todo/list 메서드\n\n"
                + "객체 : " + pageRequestDTO + "\n"
                + "페이지 : " + pageRequestDTO.getPage() + "\n"
                + "getLink : " + pageRequestDTO.getLink() + "\n"
                + "getSize : " + pageRequestDTO.getSize() + "\n"
                + "getTypes : " + pageRequestDTO.getTypes() + "\n"
                + "getKeyword : " + pageRequestDTO.getKeyword() + "\n"
                + "isFinished : " + pageRequestDTO.isFinished() + "\n"
                + "getFrom : " + pageRequestDTO.getFrom() + "\n"
                + "getTo : " + pageRequestDTO.getTo() + "\n"
        );

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }
        model.addAttribute("responseDTO", todoService.getList(pageRequestDTO));
    }


    @GetMapping(value = "/register")
    public void register() {
        log.info("\nregister GET 메서드");
    }

    @PostMapping(value = "/register")
    public String registerPOST(@Valid TodoDTO todoDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("\n/register POST 메서드");

        // 지금 제목에다가 낫널 해놨다. 확인은 TodoDTO 여기서 @NotEmpty 이걸로 낫널 적용
        if (bindingResult.hasErrors()) {
            log.info("\n데이터 입력 시 유효하지 않은 갑 들어옴, 처음 입력 화면으로 돌아감");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            // FlashAttribute 이거 일회용이다 아 그래서 일회요 ㅇ쓰는구나 이거 필수 입력창에 널 써버리면 이프문 여기로 들어오면서 다음 페이지에 에러 변수 이렇게줌
            return "redirect:/todo/register";
        }


        log.info("\n/todoDTO : " + todoDTO);

        todoService.register(todoDTO);


        return "redirect:/todo/list";
    }


    @GetMapping({"/read", "/modify"})
    public void read(Long tno, Model model, @Valid PageRequestDTO pageRequestDTO) {
        TodoDTO todoDTO = todoService.getOne(tno);

        model.addAttribute("dto", todoDTO);



        log.info("\n modify GET 매핑이다. /todoDTO 찍어봤다.  : " + todoDTO);

    }


    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            redirectAttributes.addAttribute("tno", todoDTO.getTno());
            return "redirect:/todo/modify";
        }

        log.info("\nmodify POST 매핑 todoDTO 찍어봤다. : " + todoDTO);

        todoService.modify(todoDTO);

        redirectAttributes.addAttribute("tno", todoDTO.getTno());
        redirectAttributes.addAttribute("page", pageRequestDTO.getPage());

        return "redirect:/todo/read";
    }


    @PostMapping("/remove")
    public String remove(Long tno) {

        log.info("tno: " + tno);

        todoService.remove(tno);

        return "redirect:/todo/list";
    }


}
