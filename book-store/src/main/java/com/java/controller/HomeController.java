package com.java.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.common.ServiceCommon;
import com.java.entity.Book;
import com.java.repository.BookRepository;

@Controller
public class HomeController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ServiceCommon serviceCommon;

	public HomeController(ServiceCommon serviceCommon) {
		this.serviceCommon = serviceCommon;
	}

	@GetMapping(value = "/")
	public String home(Model model) {

		listBook10(model);

		return "site/home";
	}

	@GetMapping(value = "/search")
	public String showsearch(Model model, @RequestParam("keyword") String keyword) {

		List<Book> bookList = bookRepository.searchBook(keyword);
		model.addAttribute("bookPage", bookList);
		serviceCommon.commonData(model);
		return "site/shop";
	}

	// Hiển thị Top 10 sản phẩm bán chạy nhất.
	public void listBook10(Model model) {
		List<Object[]> bookList = bookRepository.listBook10();
		if (bookList != null) {
			ArrayList<Integer> listIdBookArrayList = new ArrayList<>();
			for (int i = 0; i < bookList.size(); i++) {
				String id = String.valueOf(bookList.get(i)[0]);
				listIdBookArrayList.add(Integer.valueOf(id));
			}
			List<Book> listBooks = bookRepository.findByInventoryIds(listIdBookArrayList);
			model.addAttribute("listTop10Books", listBooks);
		}
	}

}
