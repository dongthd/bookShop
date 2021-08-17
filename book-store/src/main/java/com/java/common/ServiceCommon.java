package com.java.common;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.java.repository.AuthorRepositoy;
import com.java.repository.BookRepository;
import com.java.repository.CategoryRepository;

/**
 * @author Admin
 *
 */
@Service
public class ServiceCommon {

	@Autowired
	BookRepository bookRepository;

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	AuthorRepositoy authorRepositoy;

	public void commonData(Model model) {
		listCategoryByBookName(model);
		listAuthorByBookName(model);
		listNXBByBookName(model);
	}

	// Hiển thị mỗi thể loại sách có bao nhiêu cuốn
	public void listCategoryByBookName(Model model) {

		List<Object[]> coutnBookByCategory = bookRepository.listCategoryByBookName();
		model.addAttribute("coutnBookByCategory", coutnBookByCategory);
	}

	// Hiển thị mỗi tác giả có bao nhiêu tác phẩm
	public void listAuthorByBookName(Model model) {

		List<Object[]> coutnBookByAuthor = bookRepository.listAuthorByBookName();
		model.addAttribute("coutnBookByAuthor", coutnBookByAuthor);
	}

	// Hiển thị mỗi NXB có bao nhiêu tác phẩm
	public void listNXBByBookName(Model model) {

		List<Object[]> coutnBookByNXB = bookRepository.listNXBByBookName();
		model.addAttribute("coutnBookByNXB", coutnBookByNXB);
	}

}
