package gg.together.chat.controller;

import gg.together.chat.domain.User;
import gg.together.chat.dto.LoginRequestDto;
import gg.together.chat.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class UserController {

    @Autowired // 자동 객체 생성
    private UserService userService;

    @GetMapping("/user")
    public String user() {
        System.out.println(userService.getUser().get().getUserId());
        return "user/home";
    }

    @GetMapping("/v1")
    public String homePage(Model model) {
        model.addAttribute("pageName", "User DB");
        model.addAttribute("loginType", "v1");
        return "user/home";
    }

    @GetMapping("/{loginType}/login")
    public String loginPage(Model model, @PathVariable("loginType") String loginType) {
        model.addAttribute("pageName", "User DB");
        model.addAttribute("loginType", loginType);
        model.addAttribute("loginRequest", new LoginRequestDto());
        return "user/login";
    }

    @PostMapping("/{loginType}/login")
    public String logining(LoginRequestDto loginRequest) {
        
        // null이 뜨면 return 
        if(userService.login(loginRequest) == null){
            return "redirect:/v1/login"; 
        }

        User user = userService.login(loginRequest);
        System.out.println(user.getNickname() + "님 환영합니다. ");
        return "redirect:/v1";
    }
    
}
