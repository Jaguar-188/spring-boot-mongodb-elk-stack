package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MongoStudentRepository extends MongoRepository<Student,String> {

    @Query(value="{name:'?0'}")
    Student findByName(String name);

    @Query(value ="{id:'?0'}")
    Student findByUserId(String id);

//    @Query(value="{name:'?0'}")
//    List<Student> findAll(String name);

}
