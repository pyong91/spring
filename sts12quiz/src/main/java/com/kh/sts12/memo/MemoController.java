package com.kh.sts12.memo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kh.sts12.entity.MemoDto;
import com.kh.sts12.repository.MemoDao;

@Controller
public class MemoController {
	
	@Autowired
	private MemoDao memoDao;
	
	@GetMapping("/list")
	public String list(Model model) {
		model.addAttribute("list", memoDao.getList());
		return "/list";
	}
	
	@GetMapping("/insert" )
	public String insert() {
		return "/insert";
	}
	
	@PostMapping("/insert")
	public String insert(@ModelAttribute MemoDto memoDto) {
		memoDao.insert(memoDto);
		return "redirect:list";
	}
	
	
	
	
	
	
	
	
	
	
	
}
