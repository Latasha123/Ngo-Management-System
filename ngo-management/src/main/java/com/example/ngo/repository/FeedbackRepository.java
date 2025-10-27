package com.example.ngo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ngo.model.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, String> {
}