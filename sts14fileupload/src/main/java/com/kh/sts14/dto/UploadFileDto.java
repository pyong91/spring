package com.kh.sts14.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UploadFileDto {
	private int no;
	private String origin_name;
	private String store_name;
	private int uploader;
}
