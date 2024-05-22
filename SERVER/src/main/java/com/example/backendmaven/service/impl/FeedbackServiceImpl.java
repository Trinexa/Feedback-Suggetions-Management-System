package com.example.backendmaven.service.impl;

import com.example.backendmaven.bean.request.CommonResponse;
import com.example.backendmaven.bean.request.FeedbackReplyBean;
import com.example.backendmaven.bean.request.FeedbackRequestBean;
import com.example.backendmaven.bean.request.FeedbackResponseBean;
import com.example.backendmaven.persistant.entity.AdminUserEntity;
import com.example.backendmaven.persistant.entity.FeedbackEntity;
import com.example.backendmaven.persistant.repository.FeedbackRepo;
import com.example.backendmaven.service.FeedbackService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
@Transactional
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;

    @Autowired
    public FeedbackServiceImpl (FeedbackRepo feedbackRepo){
        this.feedbackRepo = feedbackRepo;
    }


    @Override
    public Object getFeedback(Map<String, Object> map) {
        log.debug("get Video Call My Requests  request : " );

        try {

            Page<FeedbackEntity> page = Optional.of(feedbackRepo.findAll(
                    where((root, query, cb) -> {

                        List<Predicate> predicateList = createSearchPredicate(map, cb, root);

                        return cb.and(predicateList.toArray(new Predicate[predicateList.size()]));
                    }), PageRequest.of(Integer.parseInt("0"), Integer.parseInt("10")
                    ))).orElse(Page.empty());

            return page.stream().map(this::getData).collect(Collectors.toList());

        }
        catch (Exception ex) {
            log.error("Exception  :  ", ex);
            throw ex;
        }
//        return feedbackRequestBean;

    }
    @Override
    public Object saveFeedback(FeedbackRequestBean feedbackRequestBean) {
        log.info("Request Bean : " + feedbackRequestBean.toString());
        Date date = new Date();
        Timestamp currentDate = new Timestamp(date.getTime());

        try {
            FeedbackEntity feedbackEntity = new FeedbackEntity();

            feedbackEntity.setUserId(feedbackRequestBean.getUserId());
            feedbackEntity.setFeedbackType(feedbackRequestBean.getSuggestionType());
            feedbackEntity.setRating(feedbackRequestBean.getRating());
            feedbackEntity.setFeedbackText(feedbackRequestBean.getFeedbackText());
            feedbackEntity.setFeedbackCreatedTime(currentDate);

            feedbackRepo.save(feedbackEntity);

            log.info("Start to save feedback details :" + feedbackRequestBean.getUserId());

        } catch (ObjectNotFoundException ex){
            log.error("Exception :", ex);
            throw ex;
        }
        return feedbackRequestBean;
    }

    public Object replyFeedback(FeedbackReplyBean feedbackReplyBean){
        log.info("Reply Bean : "+ feedbackReplyBean.toString());
        CommonResponse response = new CommonResponse();

        try{
            Date date = new Date();
            Timestamp currentDate = new Timestamp(date.getTime());
            // Retrieve feedback record using userId, rating, and feedback
            FeedbackEntity optionalFeedbackEntity = feedbackRepo.findFeedbackEntityByIdAndUserId(
                    feedbackReplyBean.getId(),
                    2


            );
            System.out.println("PRINT VAl - "+feedbackReplyBean.getFeedbackReply());
            System.out.println("PRINT VAl - "+feedbackReplyBean.getAdminId());
            optionalFeedbackEntity.setFeedbackReply(feedbackReplyBean.getFeedbackReply());
            optionalFeedbackEntity.setAdminUserId(2);
            optionalFeedbackEntity.setFeedbackRepliedTime(currentDate);

            feedbackRepo.save(optionalFeedbackEntity);
            response.setMessage("Change successfully");
            response.setStatus("00");
            log.info("Admin reply added successfully: " + feedbackReplyBean.getFeedbackReply());

        } catch (Exception ex){
            response.setMessage("Retry");
            response.setStatus("05");
            feedbackReplyBean.setResCode("E");
            log.error("Feedback record not found for userId: " + feedbackReplyBean.getUserId() + ", rating: "
                    + feedbackReplyBean.getRating() + ", and feedback: " + feedbackReplyBean.getFeedbackText());
        }

        return response;
    }

    public Object getFeedbackById(String id){

        long longId = Long.parseLong(id);
        try{
            FeedbackEntity feedbackEntity = feedbackRepo.findFeedbackEntityById(longId);

            FeedbackReplyBean feedbackReplyBean = new FeedbackReplyBean();
            feedbackReplyBean.setId((long) feedbackEntity.getId());
            feedbackReplyBean.setFeedbackText(feedbackEntity.getFeedbackText());
            feedbackReplyBean.setRating(feedbackEntity.getRating());
            feedbackReplyBean.setSuggestionType(feedbackEntity.getFeedbackType());

            return feedbackReplyBean;

        } catch (Exception ex){
            throw ex;
        }

    }

    private FeedbackResponseBean getData(FeedbackEntity feedback){
        FeedbackResponseBean feedbackResponseBean =new FeedbackResponseBean();
        feedbackResponseBean.setId(feedback.getId());
        feedbackResponseBean.setUserId(feedback.getUserId());
        feedbackResponseBean.setSuggestionType(feedback.getFeedbackType());
        feedbackResponseBean.setFeedbackText(feedback.getFeedbackText());
        return feedbackResponseBean;
    }

    private List<Predicate> createSearchPredicate(Map<String, ?> searchParam, CriteriaBuilder cb, Root<FeedbackEntity> root) {

        List<Predicate> predicates = new ArrayList<>();
        StringBuilder searchDetails = new StringBuilder();
//        FeedbackEntity feedbackEntity = new FeedbackEntity();

        try {
            if (searchParam.get("userId") != null) {
                predicates.add(cb.equal(root.get("userId"), searchParam.get("userId")));
            }
            if (searchParam.get("suggestionType") != null) {
                predicates.add(cb.equal(root.get("feedbackType"), searchParam.get("suggestionType")));
            }

            if (predicates.isEmpty()) {
                predicates.add(cb.conjunction());
            }


        } catch (Exception ex) {
            log.error("Exception", ex);
        }

        return predicates;
    }

    public Object deleteFeedbackById(String id){

        long longId = Long.parseLong(id);
        Integer intId = Integer.parseInt(id);
        try{
            FeedbackEntity feedbackEntity = feedbackRepo.findFeedbackEntityById(longId);
            if (feedbackEntity != null){
                feedbackRepo.deleteById(intId);
            }
            log.info("Feedback successfully deleted");

        } catch (Exception ex){
            throw ex;
        }

        return null;
    }

}
