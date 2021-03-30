package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Account;
import com.example.demo.repo.AccountRepository;

@RestController
@RequestMapping("/api")
public class AccountController {

	@Autowired
	AccountRepository accountRepository;
	
	
	@GetMapping("/accounts")
	public List<Account> getAllNotes() {
	    return accountRepository.findAll();
	}
	
	@PostMapping("/accounts")
	public Account createNote(@Validated @RequestBody Account account) {
	    return accountRepository.save(account);
	}
	
	@PutMapping("/accounts/{id}")
	public Account updateNote(@PathVariable(value = "id") Long accountid,
	                                        @Validated @RequestBody Account accountDetails) {

		Account account = accountRepository.findById(accountid)
	            .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountid));

		account.setAddress(accountDetails.getAddress());
		account.setCountry(accountDetails.getCountry());
		account.setDepartment(accountDetails.getDepartment());
		account.setEmail(accountDetails.getEmail());
		account.setName(accountDetails.getName());
		account.setPhone(accountDetails.getPhone());
		

	    Account updatedAccount = accountRepository.save(account);
	    return updatedAccount;
	}
	
	@DeleteMapping("/accounts/{id}")
	public ResponseEntity<?> deleteNote(@PathVariable(value = "id") Long accountId) {
		Account account = accountRepository.findById(accountId)
	            .orElseThrow(() -> new ResourceNotFoundException("Account", "id", accountId));

		accountRepository.delete(account);

	    return ResponseEntity.ok().build();
	}
}
