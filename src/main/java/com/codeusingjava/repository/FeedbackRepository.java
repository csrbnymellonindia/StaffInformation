package com.codeusingjava.repository;

import com.codeusingjava.model.FeedbackModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FeedbackRepository extends JpaRepository<FeedbackModel, Long> {

}