package com.ujiuye.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ujiuye.bean.Sc;
import com.ujiuye.service.ScService;

@Controller
public class ScController {
	@Autowired
	private ScService service;

	@RequestMapping("getAllSc")
	public @ResponseBody List<Sc> getAllSc(String begin, String end) {
		return service.getSc(begin, end);
	}

	public void www() {
		System.out.println("新增方法");
	}
}
