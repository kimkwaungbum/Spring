package com.weavus.board.controller;

import com.weavus.board.dto.MemberDto;
import com.weavus.board.entity.Board;
import com.weavus.board.entity.Member;
import com.weavus.board.repo.BoardRepository;
import com.weavus.board.repo.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private BoardRepository boardRepo;

    @GetMapping("/login")
    public String login(){
        return "index";
    }
    @PostMapping("/login")
    public String login(String id, String password, Model model, HttpServletRequest request){

        Member member = memberRepo.findByIdAndPw(id, password);

        if(member == null){
            model.addAttribute("msg","아이디 또는 비밀번호를 확인해주세요");
            System.out.println("로그인 실패");
            return "index";
        }else{
            HttpSession session = request.getSession();
            session.setAttribute("sessionUserName",member.getName());
            return "redirect:/board/list";
        }

    }

    @GetMapping("/signup")
    public  String signUp(){
        return "signup";
    }


    @PostMapping("/signup")
    public String signUp(MemberDto memberDto, Model model){
        if (memberRepo.findById(memberDto.getId()).isPresent()) {
            System.out.println("이미 사용하고 있는 아이디 입니다.");
            return "signup";
        }
        if (!memberDto.getPassword().equals(memberDto.getPasswordConfirmation())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return "signup";
        }

        //dto에서 entity 값옮기기
        Member member = new Member();
        member.setId(memberDto.getId());
        member.setName(memberDto.getName());
        member.setPassword(memberDto.getPassword());

        memberRepo.save(member);

       model.addAttribute("msg","회원가입 성공");


        return "index";
    }



}
