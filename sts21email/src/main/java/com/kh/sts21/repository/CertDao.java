package com.kh.sts21.repository;

import com.kh.sts21.entity.CertDto;

public interface CertDao {
	void regist(CertDto certDto);
	boolean check(String email, String cert);
	void delete(String email);
}
