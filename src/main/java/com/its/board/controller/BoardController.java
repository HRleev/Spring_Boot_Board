package com.its.board.controller;

import com.its.board.dto.BoardDTO;
import com.its.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/")
    public String findAll(Model model){
        List<BoardDTO> boardDTOList=boardService.findAll();
        model.addAttribute("boardList",boardDTOList);
        return "/boardPages/findAll";
    }
    @GetMapping("/{id}")
    public String findById(@PathVariable Long id,Model model){

        BoardDTO boardDTO=boardService.findById(id);
        model.addAttribute("board",boardDTO);
        return "boardPages/detail";
    }
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id,@ModelAttribute BoardDTO boardDTO){
        boardService.update(boardDTO);
        return "redirect:/board/"+id;
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.delete(id);
        return "redirect:/board/";
    }

}
