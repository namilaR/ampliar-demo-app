package com.amplier.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PageLoadController {
	@RequestMapping("/")
	public ModelAndView loadHomePage() {
		return new ModelAndView("index");
	}
}
