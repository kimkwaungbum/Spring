package com.weavus.board.controller;

import com.weavus.board.dto.MemberDto;
import com.weavus.board.entity.Member;
import com.weavus.board.repo.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private LoginRepository repo;

    @GetMapping("/login")
    public String login(String id, String password){

        Member member = repo.findByIdAndPw(id, password);

        if(member == null){
            System.out.println("로그인 실패");
        }else{
            System.out.println("로그인 성공");
        }
        return "index";
    }

    @GetMapping("/signup")
    public  String signUp(){
        return "signup";
    }
    @PostMapping("/signup")
    public String signUp(@RequestParam String id, @RequestParam String password,@RequestParam String name){

        System.out.println("회원가입성공");

        return "login";
    }

}
