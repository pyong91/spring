package com.kh.sts14.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// 이 클래스는 파일이 업로드 될 때를 위해 만든 클래스
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileVO {
	private String name;
	private List<MultipartFile> file;
}
