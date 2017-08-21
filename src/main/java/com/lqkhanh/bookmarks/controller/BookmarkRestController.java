package com.lqkhanh.bookmarks.controller;

import java.net.URI;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lqkhanh.bookmarks.exception.UserNotFoundException;
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

	@PostMapping
	ResponseEntity<?> add(@PathVariable String userId, @RequestBody Bookmark input) {

		this.validateUser(userId);

		return accountRepository.findByUsername(userId).map(account -> {
			Bookmark result = bookmarkRepository.save(new Bookmark(account, input.uri, input.description));

			URI location = ServletUriComponentsBuilder
					.fromCurrentRequest().path("/{id}")
					.buildAndExpand(result.getId()).toUri();
			
				return ResponseEntity.created(location).build();
		}).orElse(ResponseEntity.noContent().build());
	}
	
	@GetMapping(value = "/{bookmarkId}")
	Bookmark readBookmark (@PathVariable String userId, @PathVariable Long bookmarkId){
		this.validateUser(userId);
		return this.bookmarkRepository.findOne(bookmarkId);
	}
	

	private void validateUser(String userId) {
		this.accountRepository.findByUsername(userId).orElseThrow(() -> new UserNotFoundException(userId));
	}

}
