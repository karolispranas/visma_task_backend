package com.example.visma_task_api.controllers;

import com.example.visma_task_api.request.*;
import com.example.visma_task_api.response.GetAllMeetingsResponse;
import com.example.visma_task_api.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Validated
@RestController
@CrossOrigin
@RequestMapping(value = "/meetings")
public class Controller {

    private final MeetingService meetingService;

    @Autowired
    public Controller(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @PostMapping(value = "/add")
    ResponseEntity<?> newMeeting(@RequestBody NewMeetingRequest newMeetingRequest) {
        return ResponseEntity.ok(meetingService.addAMeeting(newMeetingRequest));
    }

    @GetMapping(value = "/get")
    @ResponseStatus(HttpStatus.OK)
    List<GetAllMeetingsResponse> fetchAllMeetings() {
        return meetingService.getAllMeetings();
    }

    @PostMapping(value = "/delete")
    ResponseEntity<?> newMeeting(@RequestBody DeleteMeetingRequest deleteMeetingRequest) {
        return ResponseEntity.ok(meetingService.deleteMeeting(deleteMeetingRequest));
    }

    @PostMapping(value = "/attendees")
    ResponseEntity<?> fetchAllAttendees(@RequestBody GetEmployeesRequest getEmployeesRequest) {
        return ResponseEntity.ok( meetingService.getAttendees(getEmployeesRequest.Id()));
    }

    @PostMapping(value = "/remaining")
    ResponseEntity<?> fetchRemainingAttendees(@RequestBody GetEmployeesRequest getEmployeesRequest){
        return ResponseEntity.ok( meetingService.getRemainingAttendees(getEmployeesRequest.Id()));
    }

    @PostMapping(value = "/addEmployee")
    ResponseEntity<?> addNewEmployee(@RequestBody AddEmployeesRequest addEmployeesRequest){
        return ResponseEntity.ok( meetingService.addEmployee(addEmployeesRequest));
    }

    @PostMapping(value = "/deleteEmployee")
    ResponseEntity<?> deleteEmployee(@RequestBody DeleteEmployeeRequest deleteEmployeeRequest){
        return ResponseEntity.ok( meetingService.deleteEmployee(deleteEmployeeRequest));
    }

}
