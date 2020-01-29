package com.kh.sts28.service;

import java.net.URISyntaxException;

import com.kh.sts28.entity.PayDto;
import com.kh.sts28.vo.KakaoPayRevokeReturnVO;
import com.kh.sts28.vo.KakaoPaySuccessReadyVO;
import com.kh.sts28.vo.KakaoPaySuccessReturnVO;
import com.kh.sts28.vo.PayReadyReturnVO;
import com.kh.sts28.vo.PayReadyVO;

public interface PayService {
	PayReadyReturnVO ready(PayReadyVO vo) throws URISyntaxException;
	KakaoPaySuccessReturnVO approve(KakaoPaySuccessReadyVO vo) throws URISyntaxException;
	KakaoPayRevokeReturnVO revoke(int no) throws URISyntaxException;
}
