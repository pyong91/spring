package com.kh.sts33.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

// 이 클래스가 데이터베이스 테이블이 되므로 그에 걸맞게 설계
@Entity
@Data
public class Music {
	@Id // primary key
	private long no;
	@Column(length = 60, nullable = false)
	private String name;
	@Column(length = 60, nullable = false)
	private String artist;
	@CreationTimestamp
	private Timestamp ctime;
	@UpdateTimestamp
	private Timestamp utime;
}
