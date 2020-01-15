package com.kh.sts13;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.sts13.entity.ProductDto;
import com.kh.sts13.repository.ProductDao;

@Controller
public class ProductController {
	
	@Autowired
	private ProductDao productDao;
	
	@GetMapping("/")
	// @ResponseBody // 여기서는 페이지명이 나가는게 아니라 값이 나간다.
	public String home() {
		return "index";
	}
	
//	상품 등록 ////////////////////////////////
	@GetMapping("/regist")
	public String regist() {
		return "regist";
	}
	
	@PostMapping("/regist")
	public String regist(@ModelAttribute ProductDto productDto) {
		return "redirect:search";
	}
//	//////////////////////////////////////
	
	@GetMapping("/search")
	public String search(
			Model model,
			@RequestParam(required = false, defaultValue="깡") String name) {
		
		List<ProductDto> list = productDao.search(name);
		model.addAttribute("list", list);
		return "search";
	}
	
	
//	@GetMapping("/search/{name}")
//	public String search(String name, Model model) {
//		model.addAttribute("list",productDao.search(name));
//		return "redirect:/search";
//	}
//	
//	@PostMapping("/search")
//	public String search(String name, Model model) {
//		model.addAttribute("list",productDao.search(name));
//		return "search";
//	}
	
}
