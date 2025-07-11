package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends MongoRepository<Account, String> {


    Account findByAccountNumber(String accountNumber);
}
