package com.kh.sts14.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kh.sts14.dto.UploadFileDto;

public class UploadFileDaoImpl implements UploadFileDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	@Override
	public void regist(UploadFileDto uploadFileDto) {
		String sql = "insert into upload_file values("
				+ "upload_fine_seq.nextval, ?, ? ?";
		
		Object[] param = {uploadFileDto.getOrigin_name(), uploadFileDto.getStore_name()};
		jdbcTemplate.update(sql, param);
		
	}	
}
