package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface MongoStudentRepository extends MongoRepository<Student,String> {

    @Query(value="{name:'?0'}")
    Student findAll(String name);

//    @Query(value="{name:'?0'}")
//    List<Student> findAll(String name);

}
