package com.kh.sts14.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.kh.sts14.dto.UploadFileDto;
import com.kh.sts14.dto.UploaderDto;
import com.kh.sts14.repository.UploadFileDao;
import com.kh.sts14.repository.UploaderDao;
import com.kh.sts14.vo.FileVO;

@Service
public class FileServiceImpl implements FileService{

	@Autowired
	private UploaderDao uploaderDao;
	
	@Autowired
	private UploadFileDao uploadFileDao;
	
	@Override
	public void store(FileVO vo) throws IllegalStateException, IOException {
//		System.out.println(vo);
		
//		vo를 이용해서 어떻게 데이터베이스에 데이터를 저장할 것인가?
//		 -> vo를 DTO로 어떻게 바꿔야 하는가?
//		 -> 1개의 UploaderDto와 여러개의 UploadFileDto로 변환(convert)
		
		int no = uploaderDao.getSequence();
		UploaderDto uploaderDto = UploaderDto.builder()
												.no(no)
												.name(vo.getName())
												.build();
		
		List<UploadFileDto> list = new ArrayList<>();
//		vo의 file을 list로 변환 및 복사
//		List<MultipartFile> ----> List<UploadFileDto>
//		 - UploadFileDto에는 변수 4개가 있는데...
//		 - store_name은 랜덤 생성
//		 - origin_name만 MultipartFile에서 복사
		
		for(MultipartFile multipartFile : vo.getFile()) {
			list.add(UploadFileDto.builder()
									.store_name(UUID.randomUUID().toString())
									.origin_name(multipartFile.getOriginalFilename())
									.uploader(no)
									.build());
		}
		
//		직접 저장할 수는 없으니 DAO를 이용하여 처리하도록 코드를 구현
//		DB저장 + 파일저장 동시에 수행
//		파일은 vo.getFile()로 구하고
//		DTO는 list에 있음
		File dir = new File("D:/upload");
		dir.mkdirs();
		for(int i=0; i<list.size(); i++) {
			MultipartFile multipartFile = vo.getFile().get(i); // 실제 파일 정보
			UploadFileDto dto = list.get(i); // DB정보
			
			File target = new File(dir, dto.getStore_name());
			multipartFile.transferTo(target); // 실제 파일저장
			uploadFileDao.regist(dto); // DB저장
			
		}
		
	}
}
