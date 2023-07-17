package com.codeusingjava.service.feedbackImpl;

import com.codeusingjava.model.FeedbackModel;
import com.codeusingjava.repository.FeedbackRepository;
import com.codeusingjava.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepository feedbackRepository;

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public List<FeedbackModel> findAllFeedbacks() {
        return feedbackRepository.findAll();
    }

    @Override
    public Optional<FeedbackModel> findFeedbackById(long feedbackIdentifier) {
        return feedbackRepository.findById(feedbackIdentifier);
    }

    @Override
    public ResponseEntity<String> addFeedback(FeedbackModel feedback) {
        if (validateFeedback(feedback)) {
            feedbackRepository.save(feedback);
            return ResponseEntity.ok().body("Feedback added successfully!");
        } else {
            return ResponseEntity.badRequest().body("Invalid feedback details");
        }
    }

    @Override
    public ResponseEntity<String> updateFeedback(long feedbackIdentifier, FeedbackModel feedback) {
        if (validateFeedback(feedback)) {
            Optional<FeedbackModel> optionalFeedback = feedbackRepository.findById(feedbackIdentifier);
            FeedbackModel existingFeedback = optionalFeedback.get();
            existingFeedback.setFeedbackIdentifier(feedback.getFeedbackIdentifier());
            existingFeedback.setUserIdentifier(feedback.getUserIdentifier());
                existingFeedback.setFeedbackDescription(feedback.getFeedbackDescription());
                feedbackRepository.save(existingFeedback);
                return ResponseEntity.ok().body("Feedback updated successfully!");

        } else {
            return ResponseEntity.badRequest().body("Invalid feedback details");
        }
    }

    @Override
    public ResponseEntity<String> deleteFeedback(long feedbackIdentifier) {
        feedbackRepository.deleteById(feedbackIdentifier);
        return ResponseEntity.ok().body("Feedback deleted successfully!");
    }

    private boolean validateFeedback(FeedbackModel feedback) {
        // Validate FEEDBACK_IDENTIFIER
        

        // Validate USER_IDENTIFIER
        if (feedback.getUserIdentifier() <= 0) {
            return false;
        }

        // Validate FEEDBACK_DESCRIPTION_TEXT
        if (feedback.getFeedbackDescription() != null && feedback.getFeedbackDescription().isEmpty()) {
            return false;
        }
        return true;
    }
}
