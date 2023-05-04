package com.main.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {

	@Autowired
	private JavaMailSender mailSender;

	public boolean sendMailPwd(String subject, String body, String to) {

		boolean issent = false;

		try {
			MimeMessage mimeMsg = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mimeMsg, true);

			helper.setSubject(subject);
			helper.setText(body, true);
			helper.setTo(to);

			mailSender.send(mimeMsg);
			issent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return issent;
	}

}
