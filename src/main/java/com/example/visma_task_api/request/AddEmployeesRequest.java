package com.example.visma_task_api.request;

import java.io.Serializable;

public record AddEmployeesRequest(

        int Id,
        String Name

)implements Serializable {
}
