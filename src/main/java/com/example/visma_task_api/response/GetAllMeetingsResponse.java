package com.example.visma_task_api.response;

import com.example.visma_task_api.objects.Employee;

import java.io.Serializable;

public record GetAllMeetingsResponse(
        String name,
        String responsiblePerson,
        String description,
        String category,
        String type,
        String startDate,
        String endDate,
        Employee[] members
) implements Serializable {}
