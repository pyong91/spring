package com.kh.sts24.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

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
	
	
	@Override
	public String AES256Encrypt(String origin, String offset) throws GeneralSecurityException, UnsupportedEncodingException{
//		순서
//		[1] 암호화를 위한 키(key)생성
//		[2] 암호화
//		
		// 키생성
		SecureRandom sr = new SecureRandom(); // 키 생성하기 위한 도구
		byte[] by = new byte[20];
		sr.nextBytes(by);
		
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WIthHmacSHA1");
		
		// 256bit의 키를 offset과 랜덤값인 by를 이용해서 생성한다.(반복횟수 : 70000)
		PBEKeySpec spec = new PBEKeySpec(offset.toCharArray(), by, 70000, 256);
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		
		
		
		// 암호화
		// CBC : Cipher Block Chaning mode
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		
		AlgorithmParameters param = cipher.getParameters();
		
		// 1단계 암호화 블록 생성
		byte[] initialBlock = param.getParameterSpec(IvParameterSpec.class).getIV();
		
		// 텍스트 암호화
		byte[] encryptTextBlock = cipher.doFinal(origin.getBytes("UTF-8")); // 기본 MS949
		
		// 결과를 합치는 작업
		//  - 결과물의 크기 = by의 길이 + initialBlock의 길이 + encryptTextBlcok의 길이
		int len = by.length + initialBlock.length + encryptTextBlock.length;
		byte[] buffer = new byte[len];
		
		// by ---> buffer(copy)
		System.arraycopy(by, 0, buffer, 0, by.length);
		
		// initialBlock ---> buffer(copy)
		System.arraycopy(initialBlock, 0, buffer, by.length, initialBlock.length);
		
		// encryptTextBlock ---> buffer(copy)
		System.arraycopy(encryptTextBlock, 0, buffer, by.length+initialBlock.length, encryptTextBlock.length);
		
		
		return Base64.getEncoder().encodeToString(buffer);
	}
	
	@Override
	public String AES256Decrypt(String origin, String offset) throws GeneralSecurityException{
		// 복원 객체 생성
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		
		// 복구작업 수행
		ByteBuffer buffer = ByteBuffer.wrap(Base64.getDecoder().decode(origin));
		
		// 분해 항목 : by, initialBlock, encryptTextBlock
		byte[] by = new byte[20];
		buffer.get(by, 0, by.length);
		
		byte[] initialBlock = new byte[cipher.getBlockSize()];
		buffer.get(initialBlock, 0, initialBlock.length);
		
		int len = buffer.capacity() - by.length - initialBlock.length;
		byte[] encryptTextBlcok = new byte[len];
		buffer.get(encryptTextBlcok);
		
		// 각각의 데이터 복구
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WIthHmacSHA1");
		PBEKeySpec spec = new PBEKeySpec(offset.toCharArray(), by, 70000, 256);
		
		SecretKey secretKey = factory.generateSecret(spec);
		SecretKeySpec secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
		
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(initialBlock));
		
		byte[] originTextBlock = cipher.doFinal(encryptTextBlcok);
		String result = new String(originTextBlock);
		return result;
	}
}
