package com.cx.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cx.board.service.BoardService;

@Controller
public class MainController {
	@Autowired
	BoardService boardService;

	@GetMapping("/")
	public String index(Model model) {
	    model.addAttribute("boardList", boardService.show());
	    return "index";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	@GetMapping("/write")
	public String write() {
		return "write";
	}
}
