package com.kh.sts15.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kh.sts15.dao.MemberDao;
import com.kh.sts15.dao.MemberProfileDao;
import com.kh.sts15.vo.MemberVO;

@Repository
public class RegistServiceImpl implements RegistService{
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private MemberProfileDao memberProfileDao;
	
	@Override
	public void store(MemberVO memberVO) throws IllegalStateException, IOException {
		
		// DB에 저장
		memberDao.insertMember(memberVO);
		memberVO.setMember_no(memberDao.getSequenceNumber(memberVO.getMember_id()));
		memberProfileDao.insertMemberProfile(memberVO);
	
		// 파일 저장
		File dir = new File("D:/upload/filetest");
		dir.mkdirs();
		File target = new File(dir, String.valueOf(memberVO.getMember_no()));
		memberVO.getMemberProfile().transferTo(target);
	
	}
}
