package com.anushka.schoolDemo.exception;

import java.time.Instant;
import java.util.Map;

public class ApiError {
    private int status;
    private String message;
    private Instant timestamp;
    private Map<String,String> errors;

    public ApiError(int status, String message, Map<String, String> errors){
        this.status = status;
        this.message = message;
        this.errors = errors;
        this.timestamp = Instant.now();
    }

    public ApiError(int status, String message){
        this(status,message, null);
    }

    public int getStatus() {return status;}
    public String getMessage() {return message;}
    public Instant getTimestamp() {return timestamp;}
    public Map<String, String> getErrors() {return errors;}
}
