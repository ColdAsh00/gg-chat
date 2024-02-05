package gg.together.chat.service;

import org.springframework.stereotype.Service;

import gg.together.chat.domain.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class SessionService {

    public void setSession(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("sessionName", user);
    }

    public String getSession(HttpServletRequest request, String sessionName) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute(sessionName);
        if(user == null){
            return null;
        } else {
            return user.getNickname();
        }
    }
}