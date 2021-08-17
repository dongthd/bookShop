package com.java.controller;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.java.common.ServiceCommon;
import com.java.entity.Author;
import com.java.repository.AuthorRepositoy;

@Controller
public class AuthorsController {

	@Autowired
	AuthorRepositoy authorRepositoy;

	@Autowired
	ServiceCommon serviceCommon;

	public AuthorsController(ServiceCommon serviceCommon) {
		this.serviceCommon = serviceCommon;
	}
	
	@GetMapping(value = "/authors")
	public String authors(Model model, Pageable pageable, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size) {

		int currentPage = page.orElse(1);
		int pageSize = size.orElse(12);

		Page<Author> authorPage = findPaginated(PageRequest.of(currentPage - 1, pageSize));

		model.addAttribute("authorPage", authorPage);
		int totalPages = authorPage.getTotalPages();
		if (totalPages > 0) {
			List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
			model.addAttribute("pageNumbers", pageNumbers);
		}

		serviceCommon.commonData(model);

		return "site/authors";
	}
	
	public Page<Author> findPaginated(Pageable pageable) {

		List<Author> authorPage = authorRepositoy.findAll();

		int pageSize = pageable.getPageSize();
		int currentPage = pageable.getPageNumber();
		int startItem = currentPage * pageSize;
		List<Author> list;

		if (authorPage.size() < startItem) {
			list = Collections.emptyList();
		} else {
			int toIndex = Math.min(startItem + pageSize, authorPage.size());
			list = authorPage.subList(startItem, toIndex);
		}

		Page<Author> authorPages = new PageImpl<Author>(list, PageRequest.of(currentPage, pageSize), authorPage.size());

		return authorPages;
	}
	
	// Hiển thị mỗi NXB có bao nhiêu tác phẩm
	public void listAuthorByBook(Model model) {

		List<Object[]> coutnBookByAuthor = authorRepositoy.listAuthorByBook();
		model.addAttribute("coutnBookByAuthor", coutnBookByAuthor);
	}

}
