package com.example.demo.repo;

import org.springframework.stereotype.Repository;

import com.example.demo.model.Account;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AccountRepository extends  JpaRepository<Account, Long>{

}
