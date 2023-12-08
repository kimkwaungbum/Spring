package com.example.board.controller;

import com.example.board.dto.BoardDto;
import com.example.board.entity.Board;
import com.example.board.repository.BoardRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("board/")
public class BoardController {
    @Autowired
    private BoardRepository boardRepo;

    @GetMapping("detail/{no}")
    private String detail(@PathVariable Integer no,  Model model) {
        Board board = boardRepo.findById(no).orElse(null);

        // 

        model.addAttribute("board", board);
        return "board/detail";
    }

    @GetMapping("delete")
    private String delete(@RequestParam Integer no, Model model) {
        boardRepo.deleteById(no);
        model.addAttribute("msg","글이 삭제 되었습니다.");
        return "redirect:/board/list";
    }
    @GetMapping("modify")
    private String moveModify(@RequestParam Integer no,Model model){
        Board board = boardRepo.findById(no).orElse(null);
        if(board != null){
            model.addAttribute("board",board);
            return "board/modify";

        }else{
            return "redirect:/board/list";

        }
    }
    @PostMapping("/modify/{no}")
    private String modify(@PathVariable Integer no, BoardDto boardDto,Model model) {

        Board board = Board.builder()
                .no(no)
                .title(boardDto.getTitle())
                .content(boardDto.getContent()).build();


        boardRepo.save(board);
        model.addAttribute("msg","정상적으로 수정이 완료 되었습니다.");
        return "redirect:/board/detail/"+no;
    }



    @GetMapping("list")
    private String list(Model model) {
        List<Board> boardList = boardRepo.findAll();
        model.addAttribute("boardList", boardList);
        return "main";
    }

    @GetMapping("reg")
    private String moveReg() {

        return "board/reg";

    }

    @PostMapping("reg")
    private String reg(BoardDto boardDto, Model model) {

        Board board = Board.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(boardDto.getWriter()).build();


        boardRepo.save(board);

        model.addAttribute("msg", "게시글이 정상 등록 되었습니다.");

        return "redirect:/board/list";

    }
}

