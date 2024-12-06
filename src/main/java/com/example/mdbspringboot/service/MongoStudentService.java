package com.example.mdbspringboot.service;

import com.example.mdbspringboot.exceptions.UserNotFoundException;
import com.example.mdbspringboot.model.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.mdbspringboot.repository.MongoStudentRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MongoStudentService {

    @Autowired
    private MongoStudentRepository studentRepository;

    @CachePut(value = "students", key = "#result.?[id]")
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

    @Cacheable(value = "students")
    public List<Student> getStudentDetails(String name,int page){

        log.info("Fetching data of {}",name);
        Pageable pageable = PageRequest.of(page,10,Sort.by("name").ascending());

        List<Student> students = new ArrayList<>();

        if(StringUtils.equalsIgnoreCase(name,"")) {
            Page<Student> pageableResponse = this.studentRepository.findAll(pageable);
            students = pageableResponse.getContent();
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
                students = this.studentRepository.findAll(pageable)
                        .stream()
                        .filter(student -> student.getName().toLowerCase().contains(name))
                        .collect(Collectors.toList());
                if (students.isEmpty()) {
                    throw new UserNotFoundException("Provided name " + name + " is not present in collection");
                }
            }
        }
        return students;
    }

    @CachePut(value = "students", key = "#result.id")
    public Student updateStudentData(Student student){

        Student studentTobeUpdated = this.studentRepository.findByUserId(student.getId());

        studentTobeUpdated.setName(student.getName());
        studentTobeUpdated.setStream(student.getStream());
        studentTobeUpdated.setHobby(student.getHobby());
        studentTobeUpdated.setClassName(student.getClassName());
        studentTobeUpdated.setHas_girlfriend(student.getHas_girlfriend());

        return this.studentRepository.save(studentTobeUpdated);
    }

    @CacheEvict(value = "students",key = "#id")
    public void deleteStudentData(String name){

        List<Student> studentList = this.studentRepository.findAll();
        for(Student student : studentList)
        {
            if(student.getName().equalsIgnoreCase(name))
            {
                this.studentRepository.deleteById(student.getId());
            }
        }
    }

    @Transactional
    public List<Student> saveBulkStudentDetails() {

        List<Student> updatedData = new ArrayList<>();
        for(int i=0;i<10;i++)
        {
            if(i == 5) break;
            List<Student> batch = new ArrayList<>();
            batch = saveBatch();
            log.info("Successfully added {} records to the collection. ",batch.size());
            updatedData.addAll(batch);
        }
        return updatedData;
    }

    public List<Student> saveBatch(){

        List<Student> updatedData = new ArrayList<>();
        List<String> names = new ArrayList<>(List.of("Collete","Collette","Collie","Colline","Colly","Con","Concettina","Conchita","Concordia","Conni","Connie","Conny","Consolata","Constance","Constancia","Constancy","Denna","Denni","Dennie","Denny","Deny","Denys","Denyse","Deonne","Desdemona","Desirae","Desiree","Desiri","Deva","Devan","Devi","Devin","Devina","Devinne","Devon","Devondra","Devonna","Devonne","Devora","Engracia","Enid","Enrica","Enrichetta","Enrika","Enriqueta","Eolanda","Eolande","Eran","Erda","Erena","Erica","Ericha","Ericka","Erika","Erin","Erina","Erinn","Erinna","Erma","Ermengarde","Ermentrude","Ermina","Erminia","Erminie","Erna","Ernaline","Ernesta","Ernestine","Ertha","Eryn","Esma","Esmaria","Guenna","Guglielma","Gui","Guillema","Guillemette","Guinevere","Guinna","Gunilla","Gus","Gusella","Gussi","Gussie","Gussy","Gusta","Gusti","Gustie","Gusty","Gwen","Gwendolen","Gwendolin","Gwendolyn","Gweneth","Gwenette","Gwenneth","Gwenni","Gwennie","Gwenny","Gwenora","Gwenore","Gwyn","Gwyneth","Gwynne","Gypsy","Hadria","Hailee","Haily","Haleigh","Halette","Haley","Hali","Halie","Halimeda","Halley","Halli","Hallie","Hally","Hana","Hanna","Hannah","Hanni","Hannie","Hannis","Hanny","Happy","Harlene","Harley","Harli","Harlie","Harmonia","Harmonie","Harmony","Harri","Harrie","Harriet","Harriett","Harrietta","Harriette","Harriot","Harriott","Hatti","Hattie","Hatty","Hayley","Hazel","Heath","Heather","Heda","Hedda","Heddi","Heddie","Hedi","Hedvig","Hedvige","Hedwig","Hedwiga","Hedy","Heida","Heidi","Heidie","Helaina"));
        List<String> classNames = new ArrayList<>(List.of("FYBTECH","SYBTECH","TYBTECH","BTECH"));
        List<String> streams = new ArrayList<>(List.of("IT","CSE","Mechanical","Electrical","Electronics","Civil","Metallurgy"));
        List<String> hobbies = new ArrayList<>(List.of("Travelling","Playing","Reading","Writing"));
        List<String> hasGirlfrind = new ArrayList<>(List.of("Yes","No"));
        for(int i=0;i<100;i++)
        {
            Student student = new Student();
            Random random = new Random();
            student.setName(names.get(random.nextInt(names.size())));
            student.setStream(streams.get(random.nextInt(streams.size())));
            student.setClassName(classNames.get(random.nextInt(classNames.size())));
            student.setHobby(hobbies.get(random.nextInt(hobbies.size())));
            student.setHas_girlfriend(hasGirlfrind.get(random.nextInt(hasGirlfrind.size())));
            Student tempStudent = this.studentRepository.save(student);
            updatedData.add(tempStudent);
            log.info("Successfully added student {}'s data to the collection. ",tempStudent.getName());
        }
        return updatedData;
    }

}
