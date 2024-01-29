package gg.together.chat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import gg.together.chat.domain.BoardEntity;
import gg.together.chat.service.BoardService;


@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;
    
    @GetMapping("/board")
    public String boardPage(Model model, String searchKeyword) {
        // searchKeyword 파라미터 값에따라 변환
        List<BoardEntity> list = searchKeyword == null ? boardService.getAllBoard() : boardService.getSearchBoard(searchKeyword);
        model.addAttribute("board", list);
        return "board/boardMain";
    }

    @GetMapping("/board/view")
    public String boardView(Model model, Integer id) {
        return "board/boardView";
    }
    
}