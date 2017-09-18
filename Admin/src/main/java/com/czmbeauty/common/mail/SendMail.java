package com.czmbeauty.common.mail;

import static com.czmbeauty.common.constants.MailConstants.FORGET_PASSWORD_MAIL_FORM;
import static com.czmbeauty.common.constants.MailConstants.FORGET_PASSWORD_MAIL_SUBJECT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component(value = "sendMail")
public class SendMail {

	/**
	 * 注入 MailSender
	 */
	@Autowired
	private MailSender mailSender;

	/**
	 * 寄送 Email
	 * 
	 * @param String
	 *            to --> 收件者
	 * @param String
	 *            from --> 寄件者
	 * @param String
	 *            subject --> 信件主旨
	 * @param String
	 *            text --> 信件內容
	 */
	private void sendMail(String to, String from, String subject, String text) {

		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

		simpleMailMessage.setTo(to);
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(text);

		mailSender.send(simpleMailMessage);
	}

	/**
	 * 忘記密碼 Email
	 * 
	 * @param String
	 *            to --> 收件者
	 * @param String
	 *            ad_password_random --> 驗證碼
	 */
	public void forgetPasswordMail(String to, String ad_password_random) {

		String from = FORGET_PASSWORD_MAIL_FORM;
		String subject = FORGET_PASSWORD_MAIL_SUBJECT;
		String text = "您的驗證碼為：" + ad_password_random + "。";

		sendMail(to, from, subject, text);
	}

}
