package com.codeusingjava.service;

import com.codeusingjava.model.FeedbackModel;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface FeedbackService {

    List<FeedbackModel> findAllFeedbacks();

    Optional<FeedbackModel> findFeedbackById(long feedbackIdentifier);

    ResponseEntity<String> addFeedback(FeedbackModel feedback);

    ResponseEntity<String> updateFeedback(long feedbackIdentifier, FeedbackModel feedback);

    ResponseEntity<String> deleteFeedback(long feedbackIdentifier);
}
