package com.example.ngo.dtos;

import lombok.Data;

@Data
public class FeedbackDto {

	private String id;
	private String donorId;
	private String ngoId;
	private String message;
}
