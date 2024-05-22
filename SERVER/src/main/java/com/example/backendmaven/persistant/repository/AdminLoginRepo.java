package com.example.backendmaven.persistant.repository;

import com.example.backendmaven.persistant.entity.AdminUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminLoginRepo extends JpaRepository<AdminUserEntity, String> {
    AdminUserEntity findAdminUserEntityByUsernameAndPassword(String username , String password);

}
