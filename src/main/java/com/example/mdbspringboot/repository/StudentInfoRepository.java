package com.example.mdbspringboot.repository;

import com.example.mdbspringboot.model.entity.StudentInfo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentInfoRepository extends MongoRepository<StudentInfo, String> {


    StudentInfo findByStudentId(String id);
}
