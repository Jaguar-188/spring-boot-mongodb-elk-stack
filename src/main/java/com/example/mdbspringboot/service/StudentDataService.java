package com.example.mdbspringboot.service;

import com.example.mdbspringboot.config.Logging;
import com.example.mdbspringboot.model.entity.Student;
import com.example.mdbspringboot.model.entity.StudentInfo;
import com.example.mdbspringboot.repository.MongoStudentRepository;
import com.example.mdbspringboot.repository.StudentInfoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StudentDataService {


    private MongoStudentRepository mongoStudentRepository;

    private StudentInfoRepository studentInfoRepository;

    private static final Logging log = Logging.getLog();
    public StudentDataService(MongoStudentRepository mongoStudentRepository, StudentInfoRepository studentInfoRepository){
        this.mongoStudentRepository = mongoStudentRepository;
        this.studentInfoRepository = studentInfoRepository;
    }

    @Transactional
    public List<Student> saveStudentDetails(List<Student> students) {
        List<Student> listOfSavedStudents = new ArrayList<>();
        Student tempStudent;
        StudentInfo studentInfo = new StudentInfo();
        StudentInfo studentInfo1;
        Random random = new Random();
        for(Student student: students)
        {
            tempStudent = mongoStudentRepository.save(student);
            studentInfo.setStudentId(tempStudent.getId());
            studentInfo.setCourse(tempStudent.getClassName());
            studentInfo.setPercentage(String.valueOf(random.nextInt(40) + 60));
            studentInfo1 = studentInfoRepository.save(studentInfo);
            listOfSavedStudents.add(tempStudent);
        }
        log.info("Successfully added student's data to the collection.");
        return listOfSavedStudents;
    }
}
