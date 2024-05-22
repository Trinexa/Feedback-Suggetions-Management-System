package com.example.backendmaven.bean.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackRequestBean {
    private Long id;
    private String suggestionType;
    private Integer rating;
    private String feedbackText;
    private Integer userId;
}
