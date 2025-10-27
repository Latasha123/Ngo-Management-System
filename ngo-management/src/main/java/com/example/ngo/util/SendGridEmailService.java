//package com.example.ngo.util;
//
//import java.io.IOException;
//
//import org.springframework.stereotype.Component;
//
//import com.sendgrid.Method;
//import com.sendgrid.Request;
//import com.sendgrid.Response;
//import com.sendgrid.SendGrid;
//import com.sendgrid.helpers.mail.Mail;
//import com.sendgrid.helpers.mail.objects.Content;
//import com.sendgrid.helpers.mail.objects.Email;
//
//@Component
//public class SendGridEmailService {
//
//	private final SendGrid sendGrid;
//
//	public SendGridEmailService(SendGrid sendGrid) {
//		this.sendGrid = sendGrid;
//	}
//
//	// Plain text email
//	public Response sendPlainTextEmail(String from, String to, String subject, String body) throws IOException {
//		Email fromEmail = new Email(from);
//		Email toEmail = new Email(to);
//		Content content = new Content("text/plain", body);
//
//		Mail mail = new Mail(fromEmail, subject, toEmail, content);
//		return execute(mail);
//	}
//
//	private Response execute(Mail mail) throws IOException {
//		Request request = new Request();
//		request.setMethod(Method.POST);
//		request.setEndpoint("mail/send");
//		request.setBody(mail.build());
//		Response response = sendGrid.api(request);
//
//		// Basic response handling (expand in real app)
//		int status = response.getStatusCode();
//		if (status >= 200 && status < 300) {
//			// success
//		} else {
//			// log or throw depending on your needs
//			System.err.println("SendGrid failure: " + status + " - " + response.getBody());
//		}
//		return response;
//	}
//}
