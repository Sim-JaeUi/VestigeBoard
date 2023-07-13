package com.vestige.board.controller;

import com.vestige.board.entity.Board;
import com.vestige.board.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @GetMapping("/") // http:localhost:8080/main
    public String mainForm() {

        return "main";
    }

    @PostMapping("/board/writepro")
    public String boardWritePro(Board board, Model model){ //, MultipartFile file

        boardService.write(board); //, files

        //if(){
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        //}else{
            //model.addAttribute("message", "글 작성이 실패하였습니다.");
        //}
        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }

    @GetMapping("/board/list")
    public String boardList(Model model, @PageableDefault(page = 0, size = 10, sort = "idx", direction = Sort.Direction.DESC) Pageable pageable, String searchKeyword) {

        Page<Board> list = null;

        if(searchKeyword == null) {
            list = boardService.boardList(pageable);
        }else {
            list = boardService.boardSearchList(searchKeyword, pageable);
        }

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

        return "boardList";
    }

    @GetMapping("board/view") // localhost:8080/board/view?idx=1
    public String boardView(Model model, Long idx) {

        model.addAttribute("board", boardService.boardView(idx));

        return "boardView";
    }

    @GetMapping("board/delete")
    public String boardDelete(Long idx) {

        boardService.boardDelete(idx);

        return "redirect:/board/list";
    }

    @GetMapping("/board/modify/{idx}")
    public String boardModify(@PathVariable("idx") Long idx, Model model) {

        model.addAttribute("board", boardService.boardView(idx));

        return "boardModify";
    }

    @PostMapping("/board/update/{idx}")
    public String boardUpdate(@PathVariable("idx") Long idx, Board board, Model model){ //MultipartFile file

        Board boardTemp = boardService.boardView(idx);
        boardTemp.setContent(board.getContent());

        boardService.write(boardTemp); // , file

        model.addAttribute("message", "글이 수정되었습니다.");

        model.addAttribute("searchUrl", "/board/list");

        return "message";
    }
}