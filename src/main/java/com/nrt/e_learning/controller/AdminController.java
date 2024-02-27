package com.nrt.e_learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AdminController {

	@GetMapping("/getAdminPage")
	public ModelAndView getSidebar(ModelAndView modelAndView) {
		modelAndView.setViewName("/html/adminPage");
		return modelAndView;
	}

}
