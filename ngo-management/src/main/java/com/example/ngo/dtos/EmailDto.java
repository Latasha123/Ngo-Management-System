package com.example.ngo.dtos;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class EmailDto {

	private String name;
	private String subject;
	private String content;
	private String fromAddress;
	private String status;
	private Boolean bccAllowed;
	private String createdBy;
	private Date createdOn;
	private String updatedBy;
	private Date updatedOn;
	private List<String> toList;
	private String to;
	private List<String> bccList;
	private List<String> ccList;
	private String attachments;

}
