package com.example.backendmaven.bean.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResponse {

    private String status;
    private String message;
    private Object content;
}
