package com.example.backendmaven.service.impl;

import com.example.backendmaven.bean.request.CommonResponse;
import com.example.backendmaven.persistant.entity.AdminUserEntity;
import com.example.backendmaven.persistant.repository.AdminLoginRepo;
import com.example.backendmaven.persistant.repository.FeedbackRepo;
import com.example.backendmaven.service.LoginService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginServiceImpl implements LoginService{

//    public FeedbackRepo feedbackRepo;
    public AdminLoginRepo adminLoginRepo;
    public Object login(String username, String password) {

        CommonResponse response = new CommonResponse();
//        Optional<AdminUserEntity> userOptional = Optional.ofNullable(feedbackRepo.findAllByUsernameAndPassword(username, password));
        AdminUserEntity userOptional = Optional.ofNullable(adminLoginRepo.findAdminUserEntityByUsernameAndPassword(username, password)).orElse(new AdminUserEntity());
//        System.out.println(userOptional);
        if(userOptional.getUsername() != null){
            response.setMessage("Login successfull");
            response.setStatus("00");
        }
        else{
            response.setMessage("Invalid username or password");
            response.setStatus("05");
        }
        return response;
    }
}
