package com.example.backendmaven.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminLoginBean {
//    private long id;
    private String username;
    private String password;
//    private String email;
//    private String full_name;

}
