package com.sunny.blog.controller;

import com.sunny.blog.domain.User;
import com.sunny.blog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final UserRepository userRepository; //글 아래에서 생성할 예정
    private final BCryptPasswordEncoder passwordEncoder; //시큐리티에서 빈 생성 예정

    @GetMapping("user")
    public String user(){
        return "user";
    }

    @GetMapping("/login")
    public String login(){
        return "loginForm";
    }

    @GetMapping("/join")
    public String join(){
        return "joinForm";
    }
    
    //조인 메소드 만들어줘야함

    @PostMapping("/join")
    public String join(User user){
        user.setRole("ROLE_ADMIN"); //권한 정보는 임시로 넣는다.
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return "redirect:/loginForm";
    }
}
