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
public class ProductDetailController {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	ServiceCommon serviceCommon;

	public ProductDetailController(ServiceCommon serviceCommon) {
		this.serviceCommon = serviceCommon;
	}

	@GetMapping(value = "productDetail")
	public String productDetail(@RequestParam("id") Integer id, Model model) {

		Book books = bookRepository.findById(id).orElse(null);
		model.addAttribute("book", books);

		serviceCommon.commonData(model);
		listBook10(model);
		listBookByCategory10(model, books.getCategory().getId());

		return "site/productDetail";
	}

	// Hiển thị Top 10 sản phẩm thịnh hành.
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

	// Gợi ý top 10 sản phẩm cùng loại
	public void listBookByCategory10(Model model, Integer categoryId) {
		
		List<Book> bookList = bookRepository.listBookByCategory10(categoryId);
		model.addAttribute("bookByCategory", bookList);

	}
}
