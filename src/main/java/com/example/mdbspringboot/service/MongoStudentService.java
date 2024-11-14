package com.example.mdbspringboot.service;

import com.example.mdbspringboot.exceptions.UserNotFoundException;
import com.example.mdbspringboot.model.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.mdbspringboot.repository.MongoStudentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class MongoStudentService {

    @Autowired
    private MongoStudentRepository studentRepository;

    public List<Student> saveStudentDetails(List<Student> students){

        List<Student> updatedData = new ArrayList<>();
        for(Student student : students)
        {
            Student tempStudent = this.studentRepository.save(student);
            log.info("Successfully added student {}'s data to the collection. ",student.getName());
            updatedData.add(tempStudent);
        }
        return updatedData;
    }

    public List<Student> getStudentDetails(String name){

        List<Student> students = new ArrayList<>();

        if(StringUtils.equalsIgnoreCase(name,"")) {
            students = this.studentRepository.findAll();
        }
        else {
//            /**
//             * Functional Approach
//             */
//            students = this.studentRepository.findAll()
//                        .stream()
//                        .filter(student -> name.equalsIgnoreCase(student.getName()))
//                        .collect(Collectors.toList());

//            /**
//             * Using Predicate
//             */
//            Predicate<? super Student> predicate = student -> name.equalsIgnoreCase(student.getName());
//            students = this.studentRepository.findAll()
//                        .stream()
//                        .filter(predicate)
//                        .collect(Collectors.toList());

            /**
             * Structured Approach
             */
            if(!StringUtils.equalsIgnoreCase(name,"") || !StringUtils.equals(name,null)) {
                for (Student student : this.studentRepository.findAll()) {
                    if (student.getName().equalsIgnoreCase(name)) {
                        students.add(student);
                    }
                    else{
                        throw new UserNotFoundException("Provided name " + name + " is not present in collection");
                    }
                }
            }
        }
        return students;
    }

    public List<Student> updateStudentData(List<Student> students){

        List<Student> studentsData = new ArrayList<>();
        List<Student> updatedList = new ArrayList<>();

        for(Student studentData :students)
        {
            Student tempStudent = this.studentRepository.findAll(studentData.getName());
            studentsData.add(tempStudent);
        }

        for(int i=0;i<studentsData.size();i++)
        {
            studentsData.get(i).setName(students.get(i).getName());
            studentsData.get(i).setClassName(students.get(i).getClassName());
            studentsData.get(i).setStream(students.get(i).getStream());
            studentsData.get(i).setHobby(students.get(i).getHobby());
            studentsData.get(i).setHas_girlfriend(students.get(i).getHas_girlfriend());
            this.studentRepository.save(studentsData.get(i));
            updatedList.add(studentsData.get(i));
        }
        return updatedList;
    }

    public void deleteStudentData(String name){

        List<Student> studentList = this.studentRepository.findAll();
        for(Student student : studentList)
        {
            if(student.getName().equalsIgnoreCase(name))
            {
                this.studentRepository.delete(student);
            }
        }
    }
}
