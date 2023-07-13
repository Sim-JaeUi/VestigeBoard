package com.vestige.board.service;

import com.vestige.board.entity.Board;
import com.vestige.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    // 게시글 작성
    public void write(Board board){ // , MultipartFile file

//        String projectPath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files"; // 프로젝트 경로 담아줌 경로지정
//
//        UUID uuid = UUID.randomUUID();
//
//        String fileName = uuid + "_" + file.getOriginalFilename(); // 랜덤으로 식별자가 붙고 _ 원래 파일이름 붙음
//
//        File saveFile = new File(projectPath, fileName);  // 파일 담아줄 껍데기 생성
//
//        file.transferTo(saveFile);
//
//        board.setFileName(fileName);
//        board.setFilePath("/files/" + fileName);

        boardRepository.save(board);
    }

    // 게시글 리스트
    public Page<Board> boardList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    public Page<Board> boardSearchList(String searchKeyword, Pageable pageable) {

        return boardRepository.findByTitleContaining(searchKeyword, pageable);
    }

    // 게시글 상세보기
    public Board boardView(Long idx) {

        return boardRepository.findById(idx).get();
    }

    // 게시글 삭제
    public void boardDelete(Long idx) {

        boardRepository.deleteById(idx);
    }
}
