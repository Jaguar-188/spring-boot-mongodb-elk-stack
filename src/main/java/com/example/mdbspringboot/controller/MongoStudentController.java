package com.example.mdbspringboot.controller;

import com.example.mdbspringboot.config.Logging;
import com.example.mdbspringboot.model.DTO.StudentDTO;
import com.example.mdbspringboot.model.entity.Student;
import com.example.mdbspringboot.service.MongoStudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("rest/v1/mongo-controller-for-crud-operations/api")
public class MongoStudentController {

    private MongoStudentService mongoStudentService;

    private final Logging log = Logging.getLog();

    public MongoStudentController(MongoStudentService mongoStudentService) {
        this.mongoStudentService = mongoStudentService;
    }

    @PostMapping(value = "/addStudentData")
    @Operation(description = "It adds the new student record/document to the collection.",
            method = "POST",
            responses = {@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Server side problem", content = @Content(mediaType = "application/json"))})
    public com.example.mdbspringboot.utils.ApiResponse addStudentData(@RequestBody List<Student> students) {

        com.example.mdbspringboot.utils.ApiResponse apiResponse = new com.example.mdbspringboot.utils.ApiResponse();
        StopWatch stopWatch = new StopWatch();
        try {

            log.info("Adding data to the collection.");
            stopWatch.start();
            apiResponse.setStudentDetails(mongoStudentService.saveStudentDetails(students));
            apiResponse.setStatusCode(String.valueOf(HttpStatus.CREATED));
            stopWatch.stop();
            log.info("Total time taken to add documents to the collection : {} seconds ",stopWatch.getTotalTimeSeconds());

        } catch (Exception e) {
            log.error("Exception occurred while adding student's data to the collection : " + e.getMessage());
            apiResponse.setMessage("Error while adding details of students.");
            apiResponse.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return apiResponse;
    }

    @PutMapping("/updateStudentData")
    @Operation(description = "It updates the record/document as per request. ",
               method = "PUT",
               responses = { @ApiResponse(responseCode = "202", description = "Accepted"),
                             @ApiResponse(responseCode = "403", description = "Bad Request")})
    public com.example.mdbspringboot.utils.ApiResponse updateStudentData(@RequestBody Student student){

        com.example.mdbspringboot.utils.ApiResponse apiResponse = new com.example.mdbspringboot.utils.ApiResponse();
        StopWatch stopWatch = new StopWatch();
        try {
            log.info("Started updating the data.");
            stopWatch.start();
            apiResponse.setStudentDetails(List.of(mongoStudentService.updateStudentData(student)));
            apiResponse.setStatusCode(String.valueOf(HttpStatus.ACCEPTED));
            stopWatch.stop();
            log.info("Total time taken to update the requested documents : {} seconds ",stopWatch.getTotalTimeSeconds());
        }
        catch(Exception e) {
            log.error("Exception occurred while updating student's data : " +e.getMessage());
            apiResponse.setMessage("Error while updating the data. ");
            apiResponse.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return apiResponse;
    }

    @PostMapping("/getStudentDetails")
    @Operation(description = "It retrieves all the students data from collection. ",
               method = "POST",
               responses = { @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
                             @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
                             @ApiResponse(responseCode = "500", description = "Server side problem", content = @Content(mediaType = "application/json"))})
    public com.example.mdbspringboot.utils.ApiResponse
    getStudentDetails(@Valid @Parameter(description = "Please provide the name.")
                      @RequestParam(required = false, defaultValue = "") String name,
                      @RequestParam(defaultValue = "0") int page){

        com.example.mdbspringboot.utils.ApiResponse apiResponse = new com.example.mdbspringboot.utils.ApiResponse();
        List<Student> students;
        StopWatch stopWatch = new StopWatch();
        try {
            log.info("Started fetching the documents from collection ");
            stopWatch.start();
            students = mongoStudentService.getStudentDetails(name);
            apiResponse.setStudentDetails(students);
            apiResponse.setMessage("Success");
            apiResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            stopWatch.stop();
            log.info("Total time required to fetch all the documents : {} seconds ",stopWatch.getTotalTimeSeconds());
        }
        catch(Exception e) {
            log.error("Exception occurred while fetching student's data : " +e.getMessage());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND).substring(0,3));
            throw e;
        }
        return apiResponse;
    }

    @DeleteMapping("/deleteStudentData")
    @Operation(description = "It deletes the requested record/document from collection",
                    method = "DELETE",
                 responses = { @ApiResponse(responseCode = "200", description = "Ok"),
                               @ApiResponse(responseCode = "404", description = "Record/Document not found", content = @Content(mediaType = "application/json"))})
    public com.example.mdbspringboot.utils.ApiResponse deleteStudentData(@RequestParam String id){

        com.example.mdbspringboot.utils.ApiResponse apiResponse = new com.example.mdbspringboot.utils.ApiResponse();
        StopWatch stopWatch = new StopWatch();
        try {
            stopWatch.start();
            mongoStudentService.deleteStudentData(id);
            apiResponse.setStatusCode(String.valueOf(HttpStatus.NO_CONTENT));
            stopWatch.stop();
            log.info("Time required to delete the document : {} seconds ",stopWatch.getTotalTimeSeconds());
        }
        catch (Exception e) {
            log.error("Exception occurred while deleting student's data : " +e.getMessage());
            apiResponse.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND));
        }
        return apiResponse;
    }

    @PostMapping(value = "/addBulkStudentData")
    @Operation(description = "It adds the new student record/document to the collection.",
            method = "POST",
            responses = {@ApiResponse(responseCode = "201", description = "Created", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Server side problem", content = @Content(mediaType = "application/json"))})
    public com.example.mdbspringboot.utils.ApiResponse addBulkStudentData() {

        com.example.mdbspringboot.utils.ApiResponse apiResponse = new com.example.mdbspringboot.utils.ApiResponse();
        StopWatch stopWatch = new StopWatch();
        try {

            log.info("Adding data to the collection.");
            stopWatch.start();
            apiResponse.setStudentDetails(mongoStudentService.saveBulkStudentDetails());
            apiResponse.setStatusCode(String.valueOf(HttpStatus.CREATED));
            stopWatch.stop();
            log.info("Total time taken to add documents to the collection : {} seconds ",stopWatch.getTotalTimeSeconds());

        } catch (Exception e) {
            log.error("Exception occurred while adding student's data to the collection : " +e.getMessage());
            apiResponse.setMessage("Error while adding details of students.");
            apiResponse.setStatusCode(String.valueOf(HttpStatus.BAD_REQUEST));
        }
        return apiResponse;
    }

    @GetMapping("/getStudentDetailsAndStudentMarks")
    @Operation(description = "It retrieves all the students data from collection. ",
            method = "GET",
            responses = { @ApiResponse(responseCode = "200", description = "Ok", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(mediaType = "application/json")),
                    @ApiResponse(responseCode = "500", description = "Server side problem", content = @Content(mediaType = "application/json"))})
    public com.example.mdbspringboot.utils.ApiResponse
    getStudentDetailsAndStudentMarks(@Valid @Parameter(description = "Please provide the name.")
                      @RequestParam(required = false, defaultValue = "") String name,
                      @RequestParam(defaultValue = "0") int page){

        com.example.mdbspringboot.utils.ApiResponse apiResponse = new com.example.mdbspringboot.utils.ApiResponse();
        List<StudentDTO> students;
        StopWatch stopWatch = new StopWatch();

        try
        {
            log.info("Started fetching the documents from collection ");
            stopWatch.start();
            students = mongoStudentService.getStudentDetailsAndMarks(name);
            apiResponse.setStudentDetails(students);
            apiResponse.setMessage("Success");
            apiResponse.setStatusCode(String.valueOf(HttpStatus.OK));
            stopWatch.stop();
            log.info("Total time required to fetch all the documents : {} seconds ",stopWatch.getTotalTimeSeconds());
        }
        catch (Exception e)
        {
            log.error("Exception occurred while fetching student's data : " +e.getMessage());
            apiResponse.setMessage(e.getMessage());
            apiResponse.setStatusCode(String.valueOf(HttpStatus.NOT_FOUND).substring(0,3));
        }
        return apiResponse;
    }
}

