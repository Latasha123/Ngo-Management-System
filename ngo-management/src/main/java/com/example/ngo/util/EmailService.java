package com.example.ngo.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.ngo.dtos.EmailDto;
import com.example.ngo.dtos.ResponseDto;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private final JavaMailSender mailSender;

	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public ResponseDto sendMail(EmailDto emailDto) {
		logger.info("[EmailService]|[sendMail]| Excecution start  ");

		ResponseDto responseDto = new ResponseDto();
		try {
			if (!ServicesUtil.isEmpty(emailDto.getToList())) {
//				SimpleMailMessage message = new SimpleMailMessage();
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper helper = new MimeMessageHelper(message, true);

				helper.setFrom("latasha0425@gmail.com");
				helper.setTo(emailDto.getTo());
				helper.setSubject(emailDto.getSubject());
				helper.setText(emailDto.getContent());
				helper.setSentDate(emailDto.getCreatedOn());

				if (emailDto.getAttachments() != null) {
					FileSystemResource file = new FileSystemResource(new File(emailDto.getAttachments()));
					helper.addAttachment(file.getFilename(), file);
				}
				mailSender.send(message);
			}
		} catch (Exception e) {
			responseDto.setStatusCode(500);
			responseDto.setStatus(false);
			responseDto.setMessage("Error occured in triggering mail" + e.getMessage());
			logger.info("[EmailService]|[sendMail]| Exception Occured  |" + e.getMessage());
		}
		logger.info("[EmailService]|[sendMail]| Excecution end  ");
		return responseDto;
	}
}
