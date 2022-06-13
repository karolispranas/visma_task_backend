package com.example.visma_task_api.request;

import java.io.Serializable;

public record DeleteEmployeeRequest(

        int Id,
        String Name,
        String ResponsiblePerson

)implements Serializable {
}
