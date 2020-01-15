package com.kh.sts12.repository;

import java.util.List;

import com.kh.sts12.entity.MemoDto;

public interface MemoDao {
	public void insert(MemoDto memoDto);
	public List<MemoDto> getList();
}
