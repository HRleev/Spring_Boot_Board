package com.its.board.controller;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/save-form")
    public String saveForm(){
        return "/boardPages/save";
    }
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO){
        Long id= boardService.save(boardDTO);
        return "redirect:/board/"+id;
    }

}
