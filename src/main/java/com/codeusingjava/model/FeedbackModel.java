package com.codeusingjava.model;

import javax.persistence.*;

@Entity
@Table(name = "FEEDBACK")
public class FeedbackModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "FEEDBACK_IDENTIFIER")
    private int feedbackIdentifier;

    @Column(name = "USER_IDENTIFIER")
    private int userIdentifier;

    @Column(name = "FEEDBACK_DESCRIPTION_TEXT")
    private String feedbackDescription;

    public int getFeedbackIdentifier() {
        return feedbackIdentifier;
    }

    public void setFeedbackIdentifier(int feedbackIdentifier) {
        this.feedbackIdentifier = feedbackIdentifier;
    }

    public int getUserIdentifier() {
        return userIdentifier;
    }

    public void setUserIdentifier(int userIdentifier) {
        this.userIdentifier = userIdentifier;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public FeedbackModel() {
    }
}
