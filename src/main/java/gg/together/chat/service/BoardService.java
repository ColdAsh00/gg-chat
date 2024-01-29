package gg.together.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gg.together.chat.domain.BoardEntity;
import gg.together.chat.repository.BoardRepository;

@Service
public class BoardService {
    
    @Autowired
    private BoardRepository boardRepository;

    // 게시글 리스트 처리
    public List<BoardEntity> getAllBoard(){
        return boardRepository.findAll();
    }

    // 키워드 검색 기능
    public List<BoardEntity> getSearchBoard(String keyword){
        return boardRepository.findByTitleContaining(keyword);
    }
}
