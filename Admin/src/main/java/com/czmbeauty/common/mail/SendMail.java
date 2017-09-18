package com.czmbeauty.common.mail;

import static com.czmbeauty.common.constants.MailConstants.FORGET_PASSWORD_MAIL_FORM;
import static com.czmbeauty.common.constants.MailConstants.FORGET_PASSWORD_MAIL_SUBJECT;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.czmbeauty.model.entity.AdminBean;

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
	 * @param to
	 *            String --> 收件者
	 * @param from
	 *            String --> 寄件者
	 * @param subject
	 *            String --> 信件主旨
	 * @param text
	 *            String --> 信件內容
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
	 * @param adminBean
	 *            AdminBean
	 * @param ad_password_random
	 *            String --> 驗證碼
	 */
	public void forgetPasswordMail(AdminBean adminBean, String ad_password_random) {

		String to = adminBean.getAd_email();
		String from = FORGET_PASSWORD_MAIL_FORM;
		String subject = FORGET_PASSWORD_MAIL_SUBJECT;
		String text = "您的帳號為: " + adminBean.getAd_username() + "，驗證碼為: " + ad_password_random + "。";

		sendMail(to, from, subject, text);
	}

}
