package com.kh.sts28.repository;

import java.util.List;

import com.kh.sts28.entity.PayDto;

public interface PayDao {
	void insertReady(PayDto payDto);
	void insertSuccess(PayDto payDto);
	List<PayDto> getList();
	PayDto get(int no);
	void insertRevoke(PayDto payDto2);
}
