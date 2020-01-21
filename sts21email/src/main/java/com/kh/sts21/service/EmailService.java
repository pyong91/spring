package com.kh.sts21.service;

import javax.mail.MessagingException;

public interface EmailService {
	String sendCertMessage(String email, String cert);
	void sendChangePasswordMail(String email) throws MessagingException;
}
