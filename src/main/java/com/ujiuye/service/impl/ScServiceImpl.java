package com.ujiuye.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ujiuye.bean.Sc;
import com.ujiuye.mapper.ScMapper;
import com.ujiuye.service.ScService;

@Service
public class ScServiceImpl implements ScService {
	@Resource
	private ScMapper scMapper;

	@Override
	public boolean saveSc(List<Sc> list) {
		return scMapper.saveSc(list);
	}

	@Override
	public List<Sc> getSc(String begin, String end) {
		return scMapper.getSc(begin, end);
	}

}
