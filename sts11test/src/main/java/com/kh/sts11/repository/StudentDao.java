package com.kh.sts11.repository;

import java.util.List;

import com.kh.sts11.entity.Student;


public interface StudentDao {
	
	void regist(Student student);
	List<Student> getList();
	List<Student> search(String keyword);
}
