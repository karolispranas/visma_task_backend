package com.example.visma_task_api.request;

import java.io.Serializable;

public record DeleteMeetingRequest(

        int Id,
        String User

) implements Serializable {
}
