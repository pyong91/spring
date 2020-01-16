package com.kh.sts15.service;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.kh.sts15.vo.MemberVO;

public interface RegistService {
	void store(MemberVO memberVO) throws IllegalStateException, IOException;
}