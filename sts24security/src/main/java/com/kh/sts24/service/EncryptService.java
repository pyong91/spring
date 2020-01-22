package com.kh.sts24.service;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public interface EncryptService {
	String caesarEncrypt(String origin, int offset);
	String caesarDecrypt(String origin, int offset);
	String xorEncrypt(String origin, int offset);
	String AES256Encrypt(String origin, String offset) throws GeneralSecurityException, UnsupportedEncodingException;
	String AES256Decrypt(String origin, String offset) throws GeneralSecurityException;
}
