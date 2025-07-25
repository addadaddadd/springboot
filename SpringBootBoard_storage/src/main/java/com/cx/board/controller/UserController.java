package com.cx.board.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cx.board.entity.UserEntity;
import com.cx.board.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping("/register.do")
	public String register(@RequestParam String id, @RequestParam String pw, @RequestParam String name,
			@RequestParam int age) {
		System.out.println("id : " + id);
		System.out.println("pw : " + pw);
		System.out.println("name : " + name);
		System.out.println("age : " + age);
		UserEntity entity = new UserEntity();
		entity.setUserId(id);
		entity.setPw(pw);
		entity.setName(name);
		entity.setAge(age);
		userService.register(entity);

		return "login";
	}

	@PostMapping("login.do")
	public String login(@RequestParam String id, @RequestParam String pw, HttpSession session) {
		Optional<UserEntity> entity = userService.login(id, pw);
		if(entity.isPresent()) { 
			session.setAttribute("user", entity.get());
			return "redirect:/";
		} else {
			return "redirect:/login?error=true";
		}
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "redirect:/";
	}
}
