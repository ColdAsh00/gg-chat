package gg.together.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.together.chat.domain.Board;
import gg.together.chat.repository.BoardRepository;

@Service
public class BoardService {
    
    @Autowired
    private BoardRepository boardRepository;

    // 게시글 리스트 처리
    public List<Board> getAllBoard(){
        return boardRepository.findAll();
    }

    // 키워드 검색 기능
    public List<Board> getSearchBoard(String keyword){
        return boardRepository.findByTitleContaining(keyword);
    }
}
