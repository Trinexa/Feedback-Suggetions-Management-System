package com.example.backendmaven.controller;

import com.example.backendmaven.bean.request.AdminLoginBean;
import com.example.backendmaven.service.LoginService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/login")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginController {

    public LoginService loginService;
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> login(@RequestBody AdminLoginBean adminLoginBean) {
        return ResponseEntity.status(HttpStatus.OK).body(loginService.login(adminLoginBean.getUsername(), adminLoginBean.getPassword()));
    }

//    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<String> login(@RequestBody AdminLoginBean adminLoginBean) {
//        String response = loginService.login(adminLoginBean.getUsername(), adminLoginBean.getPassword()).toString();
//        if ("Login successful".equals(response)) {
//            return ResponseEntity.status(HttpStatus.OK).body(response);
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//        }
//    }
}
