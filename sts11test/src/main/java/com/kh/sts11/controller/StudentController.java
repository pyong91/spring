package com.kh.sts11.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.sts11.entity.Student;
import com.kh.sts11.repository.StudentDao;

@Controller
@RequestMapping("/student")
public class StudentController {
	
	//Autowired 자료형을 기준으로 찾음
	@Autowired
	private StudentDao studentDao;
	
	@GetMapping("/list")
	public String list(Model model) {
		List<Student> list = studentDao.getList();
		model.addAttribute("list", list);
		return "student/list";
	}
	
	//	@GetMapping("/insert")
//	@PostMapping("/insert")
//	@GetMapping("/search")
	
}
