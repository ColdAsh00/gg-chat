package gg.together.chat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gg.together.chat.domain.Board;
import java.util.List;


@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
    List<Board> findByTitleContaining(String keyword);
}
