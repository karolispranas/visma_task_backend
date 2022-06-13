package com.example.visma_task_api.service;

import com.example.visma_task_api.json.JsonController;
import com.example.visma_task_api.objects.Employee;
import com.example.visma_task_api.objects.Meeting;
import com.example.visma_task_api.request.DeleteEmployeeRequest;
import com.example.visma_task_api.request.DeleteMeetingRequest;
import com.example.visma_task_api.request.AddEmployeesRequest;
import com.example.visma_task_api.request.NewMeetingRequest;
import com.example.visma_task_api.response.GetAllMeetingsResponse;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MeetingService {

    private JsonController jsonController = new JsonController();

    public String addAMeeting(NewMeetingRequest newMeetingRequest) {
        if (newMeetingRequest.Name().equals("")
                ||newMeetingRequest.Category().equals("")
                ||newMeetingRequest.Description().equals("")
                ||newMeetingRequest.ResponsiblePerson().equals("")
                ||newMeetingRequest.Type().equals("")
                ||newMeetingRequest.StartDate().equals("")
                ||newMeetingRequest.EndDate().equals(""))
        {
            return "All fields must be filled";
        }else {
            newMeeting(newMeetingRequest);
            return "New meeting has been scheduled successfully";
        }
    }

    private void newMeeting(NewMeetingRequest newMeetingRequest){
        ArrayList<Employee> members = new ArrayList<Employee>();
        members.add(jsonController.getResponsiblePerson(newMeetingRequest.ResponsiblePerson()));
        Meeting meeting = new Meeting(
                newMeetingRequest.Name(),
                newMeetingRequest.ResponsiblePerson(),
                newMeetingRequest.Description(),
                newMeetingRequest.Category(),
                newMeetingRequest.Type(),
                newMeetingRequest.StartDate(),
                newMeetingRequest.EndDate(),
                members
                );

        jsonController.saveMeeting(meeting);
    }

    public List<GetAllMeetingsResponse> getAllMeetings (){
        List<GetAllMeetingsResponse> allMeetings = jsonController.getAllMeetings().stream().collect(Collectors.toList());
        if (allMeetings.isEmpty()) {
            return new ArrayList<>();
        }
        return allMeetings;
    }

    public String deleteMeeting(DeleteMeetingRequest deleteMeetingRequest){
       return jsonController.deleteMeeting(deleteMeetingRequest);
    }

    public List<String> getAttendees( int id){
        return jsonController.getAttendees(id);
    }

    public List<String> getRemainingAttendees(int id){
        return jsonController.getRemainingAttendees(id);
    }

    public String addEmployee(AddEmployeesRequest addEmployeesRequest){
        jsonController.addEmployeeToMeeting(addEmployeesRequest);
        return "successfully invited "+ addEmployeesRequest.Name()+" to the meeting";
    }

    public String deleteEmployee(DeleteEmployeeRequest deleteEmployeeRequest){
        if (deleteEmployeeRequest.Name().equals(deleteEmployeeRequest.ResponsiblePerson())){
            return "Cannot delete this user, he is responsible for the meeting";
        }else {
            jsonController.deleteEmployeeFromMeeting(deleteEmployeeRequest);
            return "Removed user from the meeting";
        }
    }

}
