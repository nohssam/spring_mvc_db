package com.ict.email.model.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
	private JavaMailSender mailSender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;
	
	public void sendEmail(String randomNumber, String toMail) {
		MailHandler sendMail;
		try {
			sendMail = new MailHandler(mailSender);
			sendMail.setSubject("[ICT EDU 인증메일 입니다. ]"); // 메일제목
			// 내용
			sendMail.setText(""
					+ "<table><tbody>"
					+ "<tr><td><h2>ICT EDU 메일 인증</h2></td></tr>"
					+ "<tr><td><h3>ICT EDU</h3></td></tr>"
					+ "<tr><td><font size='20px'>인증번호 안내입니다.</font></td></tr>"
					+ "<tr><td></td></tr>"
					+ "<tr><td>안녕하세요 인증번화 생성되었습니다.</td></tr>"
					+ "<tr><td></td></tr>"
					+ "<tr><td><font size='20px'>확인번호 : "+randomNumber +"</font></td></tr>"
					+ "</tbody></table>");
			
			// 보내는 이
			sendMail.setFrom("nohssam73@gmail", "ICTEDU");
			
			// 받는이
			sendMail.setTo(toMail);
			sendMail.send();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}













