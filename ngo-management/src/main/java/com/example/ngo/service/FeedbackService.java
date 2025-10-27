package com.example.ngo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.ngo.model.Feedback;
import com.example.ngo.repository.FeedbackRepository;

@Service
public class FeedbackService {
	private final FeedbackRepository repo;

	public FeedbackService(FeedbackRepository repo) {
		this.repo = repo;
	}

	public Feedback save(Feedback f) {
		return repo.save(f);
	}

	public List<Feedback> listAll() {
		return repo.findAll();
	}
}