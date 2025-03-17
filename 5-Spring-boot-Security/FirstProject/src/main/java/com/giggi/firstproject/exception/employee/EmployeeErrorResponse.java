package com.giggi.firstproject.exception.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class EmployeeErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
