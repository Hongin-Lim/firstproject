package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("hi")
    public String noceToMeetYou(Model model) {
        model.addAttribute("username","hongin");
        return "greetings"; //templates/greetings.mustache -> 브라우저로 전송
    }

    @GetMapping("bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname","홍인");
        return "goodbye";
    }
}
