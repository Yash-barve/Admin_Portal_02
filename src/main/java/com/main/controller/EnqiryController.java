package com.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EnqiryController {
	
	@GetMapping("/dashboard")
	public String dashBoard() {
		return "dashboard";
	}
	
	@GetMapping("/add-enquiry")
	public String addEnquiry() {
		return "add-enquiry";
	}
	@GetMapping("/view-enquiry")
	public String viewEnquiry() {
		return "view-enquiry";
	}
}
