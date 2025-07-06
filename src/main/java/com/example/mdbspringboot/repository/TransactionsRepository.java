package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.entity.Transactions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionsRepository extends MongoRepository<Transactions, String> {


}
