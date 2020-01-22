package com.kh.sts24.service;

public interface EncryptService {
	String caesarEncrypt(String origin, int offset);
	String caesarDecrypt(String origin, int offset);
	String xorEncrypt(String origin, int offset);
}
