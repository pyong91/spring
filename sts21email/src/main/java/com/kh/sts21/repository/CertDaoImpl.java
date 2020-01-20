package com.kh.sts21.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.sts21.entity.CertDto;

@Repository
public class CertDaoImpl implements CertDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void regist(CertDto certDto) {
		sqlSession.insert("cert.regist", certDto);
	}
}
