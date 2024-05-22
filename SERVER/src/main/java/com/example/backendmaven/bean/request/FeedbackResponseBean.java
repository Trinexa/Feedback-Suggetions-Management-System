package com.example.backendmaven.bean.request;

import lombok.Data;

@Data
public class FeedbackResponseBean {
    private Integer id;
    private String suggestionType;
    private String feedbackText;
    private Integer userId;
}
