package com.cx.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cx.board.service.UserService;

@RestController
public class UserRestController {
	@Autowired
	UserService userService;
	@GetMapping("/member/check-id")
	public boolean checkId(@RequestParam String id) {
		boolean ischecked = userService.checkId(id);
		return ischecked;
	}
}
