package com.leeaain.board.controller;

import com.leeaain.board.dto.BoardDTO;
import com.leeaain.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor // final이 붙은 필드만 생성자를 만들어줌.
public class BoardController {
    private final BoardService boardService; // 생성자 의존성 주입

    @GetMapping("/save")
    public String save() {
        return "save";
    }

    @PostMapping("/save")
    // save.html의 form에서 작성한 데이터가 아래 boardDTO 객체로 넘어온다.
    public String save(BoardDTO boardDTO) {
        // 위의 boardDTO를 Service -> Repository -> DB로 전달함.
        System.out.println("**** 게시글을 작성하였습니다. ****");
        System.out.println(boardDTO);

        boardService.save(boardDTO);
        // list.html을 띄우는 게 아닌, list 요청을 보내는 것
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();

        // 위 결과를 model에 담아서 list.html로 가져간다.
        model.addAttribute("boardList", boardDTOList);
        System.out.println(" ***** boardDTOList: " + boardDTOList);
        return "list";
    }

    @GetMapping("/{id}")
        // 수조회수 처리
    public String findById(@PathVariable("id") Long id, Model model) {
        boardService.updateHits(id);

        // 상세내용 가져오기
        BoardDTO boardDTO = boardService.findById(id);

        System.out.println("!!!!!!!!! boardDTO = " + boardDTO);

        // 모델에 담아서 detail.html로 가져가겠다
        // detail.html에서 "board"로 인식함
        model.addAttribute("board", boardDTO);
        return "detail";
    }

    // 수정(update) 화면 불러오기
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model) {
        // 해당 글 본문 불러오기
        BoardDTO boardDTO = boardService.findById(id);

        // 불러온 글을 model객체에 "board" key로 바인딩
        model.addAttribute("board", boardDTO);
        return "update";
    }

//    update 페이지에서 '수정완료' 클릭하여 POST 요청 => 수정 끝나면 상세화면 띄워주기
    @PostMapping("/update/{id}")
    // update.html 폼을 제출하면, 폼 데이터는 HTTP POST 요청의 본문에 포함되어 서버로 전송
    // 여기서 파라미터에 DTO 객체를 선언해놨기 때문에 그리로 자동으로 넣어짐.
    // Spring MVC는 URL(action 속성)과 HTTP 메서드(mothod 속성)에 따라 적절한 컨트롤러 메서드를 찾음. 아래의.
    // Spring MVC가 HTTP 요청 파라미터와 BoardDTO 객체의 필드 이름을 매핑하여 자동으로 바인딩 함.
    // 그러니까, 필드 이름이 일치하는 객체이면 됨. 스프링 MVC가 필드 이름이 같은 것을 찾아서 HTTP요청 파라미터를 해당 객체의 필드에 바인딩함
    public String update(BoardDTO boardDTO, Model model) {
        // db에 저장
        boardService.update(boardDTO);

        // db에 저장한 후 저장한 데이터를 다시 불러와 담음. 조회수가 또 올라가는 걸 막기 위해(?)
        // -> findById(@GetMapping)으로 처리하면 updateHits가 실행되어 수정만 해도 조회수가 올라가기 때문
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
        // 아래처럼 작성하면 수정만 해도 조회수가 올라감....
        // return this.findById(boardDTO.getId(), model);
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.delete(id);
        return "delete";
    }

    @GetMapping("/delete")
    public String delete() {
        return "redirect:/list";
    }
}
