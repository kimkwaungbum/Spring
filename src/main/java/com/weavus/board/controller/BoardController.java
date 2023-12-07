package com.weavus.board.controller;

import com.weavus.board.dto.BoardDto;
import com.weavus.board.entity.Board;
import com.weavus.board.repo.BoardRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.lang.module.ModuleDescriptor;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepo;

    @GetMapping("/list")
    private  String list(Model model){
        List<Board> boardList= boardRepo.findAll();
        model.addAttribute("boardList",boardList);
        return "main";
    }

    @GetMapping("/reg")
    private String movereg(){
        return "board/reg";
    }

    @PostMapping("/reg")
    private String reg(BoardDto boardDto, HttpServletRequest request){

        Board board = new Board();
        board.setNo(boardDto.getNo());
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        HttpSession session = request.getSession();
        board.setWritter(session.getAttribute("sessionUserName").toString());

        boardRepo.save(board);
        return "board/regconfirm";
    }

    @GetMapping("/myList")
    private String moveMyList(Model model, HttpSession session){
        String writter = session.getAttribute("sessionUserName").toString();
        List<Board> myList=boardRepo.findByWritter(writter);
        model.addAttribute("boardList",myList);
        return "board/myList";


    }
    @GetMapping("/detail/{no}")
    private String detail(Model model, @PathVariable Integer no){
        Optional<Board> board = boardRepo.findById(no);

        if(board.isPresent()){
            model.addAttribute("board", board.get());
            return "board/detail";
        } else {
            return "redirect:/board/list";
        }
    }



    @GetMapping("/modify/{no}")
    private String moveModify(@PathVariable Integer no,Model model){
        Optional<Board> board = boardRepo.findById(no);
        if(board.isPresent()){
            model.addAttribute("board",board.get());
            return "board/modify";

        }else{
            return "redirect:/board/list";

        }
    }
    @PostMapping("/modify/{no}")
    private String modify(@PathVariable Integer no, BoardDto boardDto, HttpServletRequest request) {

        Board board = new Board();
        board.setNo(no);
        board.setTitle(boardDto.getTitle());
        board.setContent(boardDto.getContent());
        HttpSession session = request.getSession();
        board.setWritter(session.getAttribute("sessionUserName").toString());

        boardRepo.save(board);
        return "redirect:/board/detail/"+no;
    }
    @GetMapping("/delete/{no}")
    public String delete(@PathVariable("no") Integer no){
        boardRepo.deleteById(no);
        return "redirect:/board/list";
    }

}
