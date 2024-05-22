package com.example.backendmaven.persistant.repository;

import com.example.backendmaven.persistant.entity.AdminUserEntity;
import com.example.backendmaven.persistant.entity.FeedbackEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepo extends JpaRepository<FeedbackEntity, Integer>, JpaSpecificationExecutor<FeedbackEntity> {

    FeedbackEntity findFeedbackEntityByIdAndUserId(Long id, Integer userId);
    FeedbackEntity findFeedbackEntityById(Long id);
    boolean findFeedbackEntityByIdAndUsername(Long id, String s);

}
