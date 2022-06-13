package com.example.visma_task_api.request;

import java.io.Serializable;

public record NewMeetingRequest(

        String Name,
        String ResponsiblePerson,
        String Description,
        String Category,
        String Type,
        String StartDate,
        String EndDate

) implements Serializable {
}
