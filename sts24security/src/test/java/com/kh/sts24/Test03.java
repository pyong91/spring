package com.kh.sts24;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;



@Slf4j
public class Test03 {
	
//	@Test
	public void aes256encrypt() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
		
		String origin = "hello";
		String offset = "kh";
		
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
		
		String result = Base64.getEncoder().encodeToString(buffer);
		log.info("origin = {}", origin);
		log.info("result = {}", result);
	}
	
	
	@Test
	public void aes256decrypt() throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, InvalidParameterSpecException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidAlgorithmParameterException {
		String origin = "HQI8MpkG7yEU1OcTbpxna84q9PKiB4TqTbHIuvxkCZ/E0m3PmnJkYKM1IWb09b3aqJHVaQ==";
		String offset = "kh";
		
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
		
		log.info("result = {}", result);
		
	}
}
