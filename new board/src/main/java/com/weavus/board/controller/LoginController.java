package com.weavus.board.controller;

import com.weavus.board.dto.MemberDto;
import com.weavus.board.entity.Member;
import com.weavus.board.repo.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private LoginRepository repo;

    @GetMapping("/login")
    public String login(){
        return "index";
    }
    @PostMapping("/login")
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
    public String signUp(String id ,String password ,String passwordConfirmation ,String name){
        if (repo.findById(id).isPresent()) {
            System.out.println("이미 사용하고 있는 아이디 입니다.");
            return "signup";
        }
        if (!password.equals(passwordConfirmation)) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            return "signup";
        }


        return "login";
    }



}
