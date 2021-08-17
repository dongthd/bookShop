package com.java.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

	@GetMapping(value = "/carts")
	public String cart() {

		return "site/shoppingCart";
	}

	@GetMapping(value = "/checkout")
	public String checkOut() {

		return "site/checkOut";
	}
}
