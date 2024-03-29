package gg.together.chat.service;

import gg.together.chat.domain.User;
import gg.together.chat.dto.request.JoinRequest;
import gg.together.chat.dto.request.LoginRequest;
import gg.together.chat.repository.UserRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class UserService {

    @Autowired // 자동 객체 생성
    private UserRepository userRepository;

    /** userId 중복 체크 */
    public boolean checkUserIdDuplicate(String userId) {
        return userRepository.existsByUserId(userId);
    }

    /** nickname 중복 체크 */
    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }

    /** 회원가입 기능 */
    @SuppressWarnings("null")
    public void join(JoinRequest req) {
        userRepository.save(req.toEntity());
        System.out.println("성공");
    }

    /** 로그인 기능*/
    public User login(LoginRequest req) {
        Optional<User> optionalUser = userRepository.findByUserId(req.getUserId());

        // loginId와 일치하는 User가 없으면 null return
        if(optionalUser.isEmpty()) {
            System.out.println("일치하는 id가 없습니다.");
            return null;
        }

        User user = optionalUser.get();

        // 찾아온 User의 password와 입력된 password가 다르면 null return
        if(!user.getPassword().equals(req.getPassword())) {
            System.out.println("password가 일치하지 않습니다.");
            return null;
        }

        return user;
    }

    
}
