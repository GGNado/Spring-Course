package com.giggi.demo.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data


public class StudentErrorResponse {
    private int status;
    private String message;
    private long timeStamp;

}
