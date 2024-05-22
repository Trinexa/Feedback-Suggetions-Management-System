package com.example.backendmaven.service;

import com.example.backendmaven.bean.request.FeedbackReplyBean;
import com.example.backendmaven.bean.request.FeedbackRequestBean;

import java.util.Map;

public interface FeedbackService {

    Object getFeedback(Map<String, Object> map);

    Object saveFeedback(FeedbackRequestBean feedbackRequestBean);

    Object replyFeedback(FeedbackReplyBean feedbackReplyBean);
    Object getFeedbackById(String id);

    Object deleteFeedbackById(String id);

}
