package gg.together.chat.repository;

import gg.together.chat.domain.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByUserId(String userId);
    boolean existsByNickname(String nickname);
    Optional<User> findByUserId(String userId);
}
