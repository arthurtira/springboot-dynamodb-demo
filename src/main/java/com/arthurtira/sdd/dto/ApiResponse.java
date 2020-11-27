package com.arthurtira.sdd.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class  ApiResponse<T> {
    private String message;
    private List<String> errors;
    private boolean success;
    private T payload;

}
