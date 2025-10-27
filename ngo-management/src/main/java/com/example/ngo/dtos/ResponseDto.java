package com.example.ngo.dtos;

import lombok.Data;

@Data
public class ResponseDto {

	private boolean status;
	private Integer statusCode;
	private String message;
	private Object data;
	private int totalCount;
	private String action;
	private String messageName;
}
