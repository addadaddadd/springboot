package com.cx.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cx.board.entity.BoardEntity;
import com.cx.board.service.BoardService;

@RestController
public class BoardRestController {
	@Autowired
	BoardService boardService;
	@GetMapping("/board/search")
	public List<BoardEntity> search(@RequestParam String type,@RequestParam String keyword){
		return boardService.search(type, keyword);
	}

}
