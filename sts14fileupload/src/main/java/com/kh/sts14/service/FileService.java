package com.kh.sts14.service;

import java.io.IOException;

import com.kh.sts14.vo.FileVO;

public interface FileService {
//	사용자가 올린 파일을 저장하는 기능
//	이름 : store
//	매개변수 : FileVO 
//	반환형 : void
	void store(FileVO vo) throws IllegalStateException, IOException;
}
