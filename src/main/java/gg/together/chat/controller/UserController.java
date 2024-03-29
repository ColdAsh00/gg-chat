package gg.together.chat.controller;

import gg.together.chat.domain.User;
import gg.together.chat.dto.request.JoinRequest;
import gg.together.chat.dto.request.LoginRequest;
import gg.together.chat.service.SessionService;
import gg.together.chat.service.UserService;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {

    @Autowired // 자동 객체 생성
    private UserService userService;
    @Autowired
    private SessionService sessionService;

    @GetMapping("/v1")
    public String homePage(Model model, HttpServletRequest request) {
        model.addAttribute("pageName", "User DB");
        model.addAttribute("loginType", "v1");
        model.addAttribute("nickname", sessionService.getNickname(request, "sessionName"));
        return "user/home";
    }

    @GetMapping("/{loginType}/login")
    public String loginPage(Model model, @PathVariable("loginType") String loginType) {
        model.addAttribute("pageName", "User DB");
        model.addAttribute("loginType", loginType);
        model.addAttribute("loginRequest", new LoginRequest());
        return "user/login";
    }

    @PostMapping("/{loginType}/login")
    public String logining(LoginRequest loginRequest, HttpServletRequest request) {
        
        // null이 뜨면 return 
        if(userService.login(loginRequest) == null){
            return "redirect:/v1/login"; 
        }

        User user = userService.login(loginRequest);
        sessionService.setSession(request, user);
        return "redirect:/v1";
    }

    @GetMapping("/{loginType}/join")
    public String joining(Model model, @PathVariable("loginType") String loginType) {
        model.addAttribute("pageName", "User DB");
        model.addAttribute("loginType", loginType);
        model.addAttribute("joinRequest", new JoinRequest());
        return "user/join";
    }

    @PostMapping("/{loginType}/join")
    public String postMethodName(JoinRequest joinRequest) {
        
        // 중복 확인
        if(userService.checkUserIdDuplicate(joinRequest.getUserId()) 
        || userService.checkUserIdDuplicate(joinRequest.getNickname())) {
            System.out.println("id와 닉네임이 중복됩니다.");
            return "redirect:/v1/join";
        }

        // 비밀번호 다시쓰기 다름을 확인
        if(!joinRequest.getPassword().equals(joinRequest.getPasswordCheck())){
            System.out.println("비밀번호 가 똑같지 않습니다.");
            return "redirect:/v1/join";
        }

        // userService.checkNicknameDuplicate(joinRequest.getNickname());
        userService.join(joinRequest);
        System.out.println(joinRequest.getNickname() + " 님 회원가입되었습니다.");
        return "redirect:/v1";
    }

    @GetMapping("/{loginType}/info")
    public String infoPage(Model model, HttpServletRequest request) {
        model.addAttribute("pageName", "User DB");
        model.addAttribute("loginType", "v1");
        model.addAttribute("user", sessionService.getUser(request, "sessionName"));
        return "user/info";
    }

    @GetMapping("/{loginType}/logout")
    public String loginout(HttpServletRequest request) {
        sessionService.outSession(request);
        return "redirect:/v1";
    }
}
