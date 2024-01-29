package gg.together.chat.controller;

import gg.together.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired // 자동 객체 생성
    private UserService userService;


}
