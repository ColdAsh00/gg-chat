package gg.together.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ChatController {


    @GetMapping("/") // 홈 페이지
    public String getMethodName(Model model) {
        return "home";
    }

}
