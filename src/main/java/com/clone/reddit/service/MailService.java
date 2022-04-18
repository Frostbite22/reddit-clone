package com.clone.reddit.service;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.clone.reddit.exceptions.SpringRedditException;
import com.clone.reddit.model.NotificationEmail;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service 
@AllArgsConstructor
@Slf4j
public class MailService {
	private final JavaMailSender mailSender ; 
	private final MailContentBuilder mailContentBuilder ; 
	
	@Async
	void sendMail(NotificationEmail notificationEmail)
	{
		MimeMessagePreparator messagePreparator = mimeMessage -> 
		{
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
			messageHelper.setFrom("landoulsiferes@outlook.com");
			messageHelper.setTo(notificationEmail.getRecipient());
			messageHelper.setSubject(notificationEmail.getSubject());
			messageHelper.setText(notificationEmail.getBody());
		};
		try 
		{
			mailSender.send(messagePreparator);
			log.info("Activation mail sent !");
			
		}catch(MailException e)
		{
			log.error("Exception occured while sending mail",e);
			throw new SpringRedditException("Exception Occured when sending mail to "+ notificationEmail.getRecipient(),e);
		}
	}
	
}
