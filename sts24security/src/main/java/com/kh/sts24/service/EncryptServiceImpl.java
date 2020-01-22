package com.kh.sts24.service;

import org.springframework.stereotype.Service;

@Service
public class EncryptServiceImpl implements EncryptService{
	
	@Override
	public String caesarEncrypt(String origin, int offset) {

		// 변환 결과를 저장할 저장소
		StringBuffer buffer = new StringBuffer();
		
		// 글자를 반복하며 변환
		for(int i=0; i<origin.length(); i++) {
			char ch = origin.charAt(i);
			ch += offset;
			buffer.append(ch);
		}
		
		return buffer.toString();
	}
	
	@Override
	public String caesarDecrypt(String origin, int offset) {
		StringBuffer buffer = new StringBuffer();
		
		for(int i=0; i<origin.length(); i++) {
			char ch = origin.charAt(i);
			ch -= offset;
			buffer.append(ch);
		}
		
		return buffer.toString();
	}
	
	@Override
	public String xorEncrypt(String origin, int offset) {
		StringBuffer buffer = new StringBuffer();
		
		for(int i=0; i<origin.length(); i++) {
			char ch = origin.charAt(i);
			ch ^= offset;
			buffer.append(ch);
		}
		
		return buffer.toString();
	}
	
}
