package com.lqkhanh.bookmarks.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lqkhanh.bookmarks.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Optional<Account> findByUsername(String username); //select a from Account a where a.username = :username
}
