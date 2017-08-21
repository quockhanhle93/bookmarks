package com.lqkhanh.bookmarks.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lqkhanh.bookmarks.model.Bookmark;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
	Collection<Bookmark> findByAccountUsername(String username); //SELECT b from Bookmark b WHERE b.account.username = :username
}
