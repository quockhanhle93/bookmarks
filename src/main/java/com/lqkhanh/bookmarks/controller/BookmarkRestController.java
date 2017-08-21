package com.lqkhanh.bookmarks.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lqkhanh.bookmarks.model.Bookmark;
import com.lqkhanh.bookmarks.repository.AccountRepository;
import com.lqkhanh.bookmarks.repository.BookmarkRepository;

@RestController
@RequestMapping("/api/{userId}/bookmarks")
public class BookmarkRestController {

	private BookmarkRepository bookmarkRepository;
	private AccountRepository accountRepository;

	@Autowired
	public BookmarkRestController(BookmarkRepository bookmarkRepository, AccountRepository accountRepository) {
		super();
		this.bookmarkRepository = bookmarkRepository;
		this.accountRepository = accountRepository;
	}

	@GetMapping
	Collection<Bookmark> readBookMarks(@PathVariable String userId) {
		this.validateUser(userId);

		return this.bookmarkRepository.findByAccountUsername(userId);
	}
	

	private void validateUser(String userId) {
		// TODO Auto-generated method stub

	}

}
