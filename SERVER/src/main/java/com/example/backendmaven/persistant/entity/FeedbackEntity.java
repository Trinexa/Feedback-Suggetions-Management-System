package com.example.backendmaven.persistant.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@NoArgsConstructor
@Entity
@Table(name = "feedback", schema = "nebulos", catalog = "")
public class FeedbackEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "feedback_type", nullable = false, length = 255)
    private String feedbackType;
    @Basic
    @Column(name = "rating", nullable = true)
    private Integer rating;
    @Basic
    @Column(name = "feedback_text", nullable = true, length = -1)
    private String feedbackText;
    @Basic
    @Column(name = "user_id", nullable = true)
    private Integer userId;
    @Basic
    @Column(name = "feedback_created_time", nullable = true)
    private Timestamp feedbackCreatedTime;
    @Basic
    @Column(name = "feedback_reply", nullable = true, length = -1)
    private String feedbackReply;
    @Basic
    @Column(name = "feedback_status", nullable = true)
    private Integer feedbackStatus;
    @Basic
    @Column(name = "feedback_replied_time", nullable = true)
    private Timestamp feedbackRepliedTime;
    @Basic
    @Column(name = "admin_user_id", nullable = true)
    private Integer adminUserId;
    @Basic
    @Column(name ="username", nullable = true ,  length = 255)
    private String username;
    @Basic
    @Column(name ="password", nullable = true , length = 255)
    private String password;
}