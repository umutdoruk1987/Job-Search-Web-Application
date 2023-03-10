package com.umutdoruk.jobSearch.controllers.employer;

import com.umutdoruk.jobSearch.dto.employer.WorkingTimeRequest;
import com.umutdoruk.jobSearch.dto.employer.WorkingTimeResponse;
import com.umutdoruk.jobSearch.service.employer.WorkingTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/working_time/")
public class WorkingTimeController {

    private final WorkingTimeService workingTimeService;

    @Autowired
    public WorkingTimeController(WorkingTimeService workingTimeService) {
        this.workingTimeService = workingTimeService;
    }

    @PostMapping() // ROLE_EMPLOYER
    public ResponseEntity<WorkingTimeResponse> create(@RequestBody WorkingTimeRequest workingTimeRequest){
        WorkingTimeResponse workingTimeResponse = workingTimeService.create(workingTimeRequest);
        return new ResponseEntity<>(workingTimeResponse,HttpStatus.CREATED);
    }

    @PutMapping() // ROLE_EMPLOYER
    public ResponseEntity<WorkingTimeResponse> update(@RequestBody WorkingTimeRequest workingTimeRequest) {
        WorkingTimeResponse workingTimeResponse = workingTimeService.update(workingTimeRequest);
        return new ResponseEntity<>(workingTimeResponse, HttpStatus.OK);
    }

    @DeleteMapping("{id}")  // ROLE_EMPLOYER
    public HttpStatus delete(@PathVariable("id") Long id) {
        workingTimeService.delete(id);
        return HttpStatus.OK;
    }

    @GetMapping("id/{id}") // ROLE_EMPLOYER
    public ResponseEntity<WorkingTimeResponse> getWorkingTimeResponseById (@PathVariable("id") Long id){
        WorkingTimeResponse workingTimeResponse = workingTimeService.getWorkingTimeResponseById(id);
        return new ResponseEntity<>(workingTimeResponse,HttpStatus.OK);
    }

   /* @GetMapping("name/{workingTimeName}")  // ROLE_EMPLOYER
    public ResponseEntity<List<WorkingTimeResponse>> getWorkingTimeResponseByName (@PathVariable("workingTimeName") String workingTimeName){
        List<WorkingTimeResponse> workingTimeResponseList = workingTimeService.getWorkingTimeResponseByName(workingTimeName);
        return new ResponseEntity<>(workingTimeResponseList, HttpStatus.OK);
    }
*/
    @GetMapping("getAll") // ROLE_EMPLOYER
    public ResponseEntity<List<WorkingTimeResponse>> getAllWorkingTimeResponses(){
        List<WorkingTimeResponse> workingTimeResponseList = workingTimeService.getAllWorkingTimeResponses();
        return new ResponseEntity<>(workingTimeResponseList, HttpStatus.OK);
    }
}
