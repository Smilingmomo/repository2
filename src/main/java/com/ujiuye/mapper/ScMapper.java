package com.ujiuye.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.ujiuye.bean.Sc;

public interface ScMapper {
	public boolean saveSc(List<Sc> list);

	public List<Sc> getSc(@Param("begin") String begin, @Param("end") String end);
}
