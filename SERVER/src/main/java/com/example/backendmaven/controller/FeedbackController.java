package com.example.backendmaven.controller;
import com.example.backendmaven.bean.request.AdminLoginBean;
import com.example.backendmaven.bean.request.FeedbackReplyBean;
import com.example.backendmaven.bean.request.FeedbackResponseBean;
import com.example.backendmaven.service.FeedbackService;
import com.example.backendmaven.bean.request.FeedbackRequestBean;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

//identifiy controller
@RestController
//endpoints
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/feedback")
public class FeedbackController {
    private FeedbackService feedbackService;

    @Autowired
    public FeedbackController(FeedbackService feedbackService) {
       this.feedbackService = feedbackService;
    }

//    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> getFeedback(@RequestAttribute(value = "username") String username)
//    {
//        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getData(username));
//    }

    @PostMapping(value = "/submit", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveFeedback(@RequestBody FeedbackRequestBean feedbackRequestBean)
    {
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.saveFeedback(feedbackRequestBean));
    }


    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFeedback(

            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String suggestionType
            ) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("suggestionType", suggestionType);

        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getFeedback(map));
    }

//    @GetMapping(value = "/id",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Object> getFeedback(@RequestBody FeedbackResponseBean feedbackResponseBean){
//        feedbackService.getFeedback(feedbackResponseBean);
//        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getFeedback(feedbackResponseBean));
//    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getFeedbackById(@PathVariable String id)
    {
        System.out.println("DashboardController assignToMe method call");
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.getFeedbackById(id));
    }


    @PostMapping(value = "/reply",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> replyFeedback(@RequestBody FeedbackReplyBean feedbackReplyBean){
        feedbackService.replyFeedback((feedbackReplyBean));
        return new ResponseEntity<>("Replied feedback successfully",HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteFeedbackById(@PathVariable String id)
    {
        System.out.println("delete feedback successfully.");
        return ResponseEntity.status(HttpStatus.OK).body(feedbackService.deleteFeedbackById(id));
    }

}
