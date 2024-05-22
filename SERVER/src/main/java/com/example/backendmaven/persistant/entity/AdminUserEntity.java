package com.example.backendmaven.persistant.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "adminusers", schema = "nebulos", catalog = "")
public class AdminUserEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Basic
    @Column(name ="username", nullable = true ,  length = 255)
    private String username;
    @Basic
    @Column(name ="password", nullable = true , length = 255)
    private String password;
}
