package com.kh.sts33;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kh.sts33.entity.Music;
import com.kh.sts33.repository.MusicRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class JpaCrudTest {
	
	@Autowired
	private MusicRepository repo;
	
//		등록, 수정 : save
//	@Test
//	public void test() {
//		log.info("repo = {}", repo);
//		Music m = new Music();
//		m.setNo(1L);
//		m.setName("아무노래");
//		m.setArtist("지아코");
//		repo.save(m);
//	}
	
//		목록 : findAll(), findById(), count()
//	@Test
//	public void test() {
//		log.info("노래 개수 : {}", repo.count());
//		List<Music> list = repo.findAll();
//		for (Music m : list) {
//			log.info("m = {}", m);
//		}
//	}
	
//		수정 : findById --> save
	@Test
	public void edit() {
		Music m = repo.findById(1L).orElse(null);
		m.setName("아무노래2");
		m.setArtist("Zico");
		repo.save(m);
	}
	
}




