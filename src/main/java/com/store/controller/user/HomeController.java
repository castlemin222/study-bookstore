package com.store.controller.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.store.request.UserRegisterForm;
import com.store.service.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class HomeController {

	private final UserService userService;
	
	// 홈 화면 요청
	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	// 회원가입 화면 요청
	@GetMapping("/register")
	public String registerForm(Model model) {
		model.addAttribute("registerFrom", new UserRegisterForm());
		
		return "register-form";
	}
	
	// 회원가입
	@PostMapping("/register")
	public String register(UserRegisterForm form) {
		userService.insertUser(form);
		
		return "redirect:success";
	}
	
	// 회원가입 성공 화면
	@GetMapping("/success")
	public String success() {
		return "success";
	}

	// 로그인 화면 요청
	@GetMapping("/login")
	public String loginform() {
		return "login-form";
	}
	
}
