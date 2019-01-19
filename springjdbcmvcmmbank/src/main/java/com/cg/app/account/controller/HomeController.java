package com.cg.app.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

	@RequestMapping("/index")
	public String home() {
		return "index";
	}
	@RequestMapping("/savings")
	public String savingsMenu()
	{
		return "SavingsAccountForm";
	}
	@RequestMapping("/current")
	public String currentMenu()
	{
		return "CurrentAccountForm";
	}
}