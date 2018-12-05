package com.ujiuye.service;

import java.util.List;
import com.ujiuye.bean.Sc;

public interface ScService {
	public boolean saveSc(List<Sc> list);

	public List<Sc> getSc(String begin, String end);
}
