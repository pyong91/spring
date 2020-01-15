package com.kh.sts14.repository;

import com.kh.sts14.dto.UploaderDto;

public interface UploaderDao {
	int getSequence();
	void regist(UploaderDto uploaderDto);
}
