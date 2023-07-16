package com.codeusingjava.controller;

import com.codeusingjava.model.FeedbackModel;
import com.codeusingjava.service.FeedbackService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/feedbacks")
public class FeedbackController {

    private final FeedbackService feedbackService;

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @PostMapping("/addFeedback")
    public ResponseEntity<String> addFeedback(@RequestBody FeedbackModel feedback) {
        return feedbackService.addFeedback(feedback);
    }

    @PutMapping("/updateFeedback/{feedbackIdentifier}")
    public ResponseEntity<String> updateFeedback(@PathVariable long feedbackIdentifier, @RequestBody FeedbackModel feedback) {
        return feedbackService.updateFeedback(feedbackIdentifier, feedback);
    }

    @DeleteMapping("/deleteFeedback/{feedbackIdentifier}")
    public ResponseEntity<String> deleteFeedback(@PathVariable long feedbackIdentifier) {
        return feedbackService.deleteFeedback(feedbackIdentifier);
    }

    @GetMapping("/getAll")
    public List<FeedbackModel> getAllFeedbacks() {
        return feedbackService.findAllFeedbacks();
    }

    @GetMapping("/getFeedback/{feedbackIdentifier}")
    public ResponseEntity<FeedbackModel> getFeedbackById(@PathVariable int feedbackIdentifier) {
        Optional<FeedbackModel> feedback = feedbackService.findFeedbackById(feedbackIdentifier);
        return feedback.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
