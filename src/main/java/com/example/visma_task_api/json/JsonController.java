package com.example.visma_task_api.json;

import com.example.visma_task_api.objects.Employee;
import com.example.visma_task_api.objects.Meeting;
import com.example.visma_task_api.request.DeleteEmployeeRequest;
import com.example.visma_task_api.request.DeleteMeetingRequest;
import com.example.visma_task_api.request.AddEmployeesRequest;
import com.example.visma_task_api.response.GetAllMeetingsResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.json.simple.JSONObject;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class JsonController {

    public void saveMeeting(Meeting meeting) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Meeting> meetings = mapper.readValue(new File("meetings.json"), new TypeReference<>(){});
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            meetings.add(meeting);
            writer.writeValue(Paths.get("meetings.json").toFile(), meetings);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<GetAllMeetingsResponse> getAllMeetings(){
        List<GetAllMeetingsResponse> meetings = Collections.emptyList();
        try {
            ObjectMapper mapper = new ObjectMapper();
            meetings = mapper.readValue(new File("meetings.json"), new TypeReference<>(){});
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return meetings;
    }

    public Employee getResponsiblePerson(String name){
        Employee responsiblePerson = new Employee();
        try {
            List<JSONObject> employees ;
            ObjectMapper mapper = new ObjectMapper();
            employees = mapper.readValue(new File("employees.json"), new TypeReference<>(){});
            for (JSONObject employee : employees) {
                if (employee.get("name").equals(name)){
                    responsiblePerson.setName((String) employee.get("name"));
                    responsiblePerson.setAge((int) employee.get("age"));
                    responsiblePerson.setRole((String) employee.get("role"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return responsiblePerson;
    }

    public String deleteMeeting(DeleteMeetingRequest deleteMeetingRequest){
        String responseText = "";
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Meeting> meetings = mapper.readValue(new File("meetings.json"), new TypeReference<>(){});
            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            if (deleteMeetingRequest.User().equals(meetings.get(deleteMeetingRequest.Id()).getResponsiblePerson())){
                meetings.remove(deleteMeetingRequest.Id());
                responseText = "Meeting deleted";
                writer.writeValue(Paths.get("meetings.json").toFile(), meetings);
            }else {
                responseText = "The user cannot delete this meeting";
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return responseText;
    }

    public List<String> getAttendees(int id){
        List<Meeting> meetings = Collections.emptyList();
        try {

            ObjectMapper mapper = new ObjectMapper();
            meetings = mapper.readValue(new File("meetings.json"), new TypeReference<>(){});

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        List<Employee> attendees = meetings.get(id).getMembers();
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0 ; i<attendees.size(); i++){
            names.add(i,attendees.get(i).getName());
        }
        return names;
    }

    public List<String> getRemainingAttendees(int id){
        List<Meeting> meetings = Collections.emptyList();
        List<Employee> employees = Collections.emptyList();
        try {

            ObjectMapper mapper = new ObjectMapper();
            meetings = mapper.readValue(new File("meetings.json"), new TypeReference<>(){});
            employees = mapper.readValue(new File("employees.json"), new TypeReference<>(){});

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        List<Employee> attendees =  meetings.get(id).getMembers();
        ArrayList<String> employeesNames = new ArrayList<>();
        ArrayList<String> attendeesNames = new ArrayList<>();

        for (Employee employee : employees) {
            employeesNames.add(employee.getName());
        }
        for (Employee attendant : attendees) {
            attendeesNames.add(attendant.getName());
        }

        employeesNames.removeAll(attendeesNames);

        return employeesNames;
    }

    public void addEmployeeToMeeting(AddEmployeesRequest addEmployeesRequest){

        try {
            Employee newEmployee = new Employee();
            List<Meeting> meetings;
            List<JSONObject> employees;
            Meeting editedMeeting;

            ObjectMapper mapper = new ObjectMapper();

            meetings = mapper.readValue(new File("meetings.json"), new TypeReference<>(){});
            employees = mapper.readValue(new File("employees.json"), new TypeReference<>(){});
            for (JSONObject employee : employees) {
                if (employee.get("name").equals(addEmployeesRequest.Name())){
                    newEmployee.setName((String) employee.get("name"));
                    newEmployee.setAge((int) employee.get("age"));
                    newEmployee.setRole((String) employee.get("role"));
                }
            }
            editedMeeting = meetings.get(addEmployeesRequest.Id());
            Boolean isAlreadyPartOfMeeting = false;
            for (Employee employee: editedMeeting.getMembers()){
                if (employee.getName().equals(newEmployee.getName())){
                    isAlreadyPartOfMeeting=true;
                }
            }
            if (isAlreadyPartOfMeeting==false){
                editedMeeting.addMember(newEmployee);
                ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
                meetings.remove(addEmployeesRequest.Id());
                meetings.add( editedMeeting);
                writer.writeValue(Paths.get("meetings.json").toFile(), meetings);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    public void deleteEmployeeFromMeeting(DeleteEmployeeRequest deleteEmployeeRequest){
        try {
            Employee newEmployee = new Employee();
            List<Meeting> meetings;
            List<JSONObject> employees;
            Meeting editedMeeting;

            ObjectMapper mapper = new ObjectMapper();

            meetings = mapper.readValue(new File("meetings.json"), new TypeReference<>(){});
            employees = mapper.readValue(new File("employees.json"), new TypeReference<>(){});

            for (JSONObject employee : employees) {
                if (employee.get("name").equals(deleteEmployeeRequest.Name())){
                    newEmployee.setName((String) employee.get("name"));
                    newEmployee.setAge((int) employee.get("age"));
                    newEmployee.setRole((String) employee.get("role"));
                }
            }

            editedMeeting = meetings.get(deleteEmployeeRequest.Id());

            editedMeeting.removeMember(newEmployee);

            ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());
            meetings.remove(deleteEmployeeRequest.Id());
            meetings.add( editedMeeting);
            writer.writeValue(Paths.get("meetings.json").toFile(), meetings);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}

