package com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")

public class TemplateController {

	@GetMapping("login")
	String getLoginView() {
		return "login";
	}
	@GetMapping("courses")
	String getCoursesView() {
		return "courses";
	}
	@GetMapping("logout")
	String getLogoutView() {
		return "logout";
	}
}
