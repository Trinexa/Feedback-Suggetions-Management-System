package com.example.backendmaven.service;

import com.example.backendmaven.bean.request.AdminLoginBean;

public interface LoginService {
    Object login(String username, String password);
//    Object login(AdminLoginBean adminLoginBean);
}
